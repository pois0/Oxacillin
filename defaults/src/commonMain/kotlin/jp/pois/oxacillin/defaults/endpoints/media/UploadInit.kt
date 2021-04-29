/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
 *     Copyright (c) 2021 poispois
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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.pois.oxacillin.defaults.endpoints.media

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.Media
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.media
import jp.pois.oxacillin.endpoints.media.MediaCategory
import jp.pois.oxacillin.endpoints.media.MediaType
import jp.pois.oxacillin.endpoints.media.uploadInit

/**
 * The INIT command request is used to initiate a file upload session. It returns a media_id which should be used to execute all subsequent requests. The next step after a successful return from INIT command is the [APPEND command](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload-append).
 * See the [Uploading media guide](https://developer.twitter.com/en/docs/media/upload-media/uploading-media/media-best-practices) for constraints and requirements on media files.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload-init)
 * 
 * @param totalBytes The size of the media being uploaded in bytes.
 * @param mediaType The MIME type of the media being uploaded.
 * @param mediaCategory A string enum value which identifies a media usecase. This identifier is used to enforce usecase specific constraints (e.g. file size, video duration) and enable advanced features.
 * @param additionalOwners A comma-separated list of user IDs to set as additional owners allowed to use the returned media_id in Tweets or Cards. Up to 100 additional owners may be specified.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Media] endpoint instance.
 * @return [JsonGeneralApiAction] for [jp.pois.oxacillin.defaults.models.Media] model.
 */
public inline fun Media.uploadInit(
    totalBytes: Int,
    mediaType: MediaType,
    mediaCategory: MediaCategory = MediaCategory.Default,
    additionalOwners: List<Long>? = null,
    vararg options: Option
): JsonGeneralApiAction<jp.pois.oxacillin.defaults.models.Media> = client.media.uploadInit(totalBytes, mediaType, mediaCategory, additionalOwners, *options)
