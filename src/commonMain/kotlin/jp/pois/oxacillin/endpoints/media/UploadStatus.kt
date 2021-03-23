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

package jp.pois.oxacillin.endpoints.media

import jp.pois.oxacillin.core.request.EndpointHost
import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Media
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.util.deserializer
import io.ktor.http.LinkHeader.Parameters.Media
import kotlinx.serialization.DeserializationStrategy

/**
 * The STATUS command is used to periodically poll for updates of media processing operation. After the STATUS command response returns succeeded, you can move on to the next step which is usually create Tweet with media_id.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/media/upload-media/api-reference/get-media-upload-status)
 * 
 * @param mediaId The media_id returned from the INIT command.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Media] endpoint instance.
 * @return [JsonGeneralApiAction] for [jp.pois.oxacillin.models.Media] model.
 */
public fun <T> Media.uploadStatus(
    deserializer: DeserializationStrategy<T>,
    mediaId: Long,
    mediaKey: String? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.get("/1.1/media/upload.json", EndpointHost.MediaUpload) {
    parameters(
        "command" to "STATUS",
        "media_id" to mediaId,
        "media_key" to mediaKey,
        *options
    )
}.json(deserializer)

public inline fun <reified T> Media.uploadStatus(
    mediaId: Long,
    mediaKey: String? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = uploadStatus(deserializer(), mediaId, mediaKey, *options)
