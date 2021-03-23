/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:Suppress("UNUSED")

package jp.pois.oxacillin.extensions.endpoints

import jp.pois.oxacillin.core.request.action.ApiAction
import jp.pois.oxacillin.endpoints.Media
import jp.pois.oxacillin.endpoints.media.MediaComponent
import jp.pois.oxacillin.endpoints.media.uploadAppend
import jp.pois.oxacillin.endpoints.media.uploadFinalize
import jp.pois.oxacillin.endpoints.media.uploadInit
import jp.pois.oxacillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

private const val segmentMaxSize = 5 * 1024 * 1024

/**
 * Use this endpoint to upload images to Twitter. It returns a media_id which can be used in most Twitter endpoints that accept images. For example, a media_id value can be used to create a Tweet with an attached photo using the [POST statuses/update](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-update#post-statuses-update) endpoint.
 * This is a simple image upload endpoint, with a limited set of features. The preferred alternative is the chunked upload endpoint which supports both images and videos, provides better reliability, allows resumption of file uploads, and other important features. In the future, new features will only be supported for the chunked upload endpoint.
 * 
 * See the [Uploading media guide](https://developer.twitter.com/en/docs/media/upload-media/uploading-media/media-best-practices) for constraints and requirements on media files.
 * Use the [media metadata endpoint](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-metadata-create) to provide image alt text information.
 * Ensure the POST is a multipart/form-data request. Either upload the raw binary (media parameter) of the file, or its base64-encoded contents (media_data parameter). Use raw binary when possible, because base64 encoding results in larger file sizes
 * The response provides a media identifier in the media_id (64-bit integer) and media_id_string (string) fields. Use the media_id_string provided in the API response from JavaScript and other languages that cannot accurately represent a long integer.
 * The returned media_id is only valid for expires_after_secs seconds. Any attempt to use media_id after this time period in other endpoints will result in an HTTP 4xx Bad Request.
 * The additional_owners field enables media to be uploaded media as user A and then used to create Tweets as user B.
 * Please note that for certain types of data (tweet_gif, tweet_video and amplify_video), you need to use the [chunked upload end-point](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload-init).
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload)
 *
 * @receiver [Media] endpoint instance.
 * @return [DelegatedAction] for [jp.pois.oxacillin.models.Media] model.
 */
public fun <T: jp.pois.oxacillin.models.Media> Media.uploadMedia(
    deserializer: DeserializationStrategy<T>,
    media: MediaComponent
): ApiAction<T> = DelegatedAction {
    val init = uploadInit(
        deserializer,
        totalBytes = media.data.size,
        mediaType = media.type,
        mediaCategory = media.category
    ).execute()

    media.data.asSequence()
        .chunked(segmentMaxSize)
        .forEachIndexed { i, part ->
            uploadAppend(
                media = MediaComponent(
                    data = part.toByteArray(),
                    type = media.type,
                    category = media.category
                ),
                mediaId = init.result.mediaId,
                segmentIndex = i,
                mediaKey = init.result.mediaKey
            ).execute()
        }

    uploadFinalize(
        deserializer,
        mediaId = init.result.mediaId,
        mediaKey = init.result.mediaKey
    ).execute().result
}

public inline fun <reified T: jp.pois.oxacillin.models.Media> Media.uploadMedia(media: MediaComponent): ApiAction<T> = uploadMedia(deserializer(), media)
