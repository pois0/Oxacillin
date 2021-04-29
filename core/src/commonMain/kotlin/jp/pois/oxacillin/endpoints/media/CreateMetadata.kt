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

@file:Suppress("UNUSED")

package jp.pois.oxacillin.endpoints.media

import jp.pois.oxacillin.core.request.EndpointHost
import jp.pois.oxacillin.core.request.action.EmptyApiAction
import jp.pois.oxacillin.core.request.buildJsonBody
import jp.pois.oxacillin.core.request.empty
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Media
import jp.pois.oxacillin.endpoints.Option
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.put

/**
 * This endpoint can be used to provide additional information about the uploaded media_id. This feature is currently only supported for images and GIFs.
 * The request flow should be:
 * 1. Upload media using either the simple upload endpoint or the (preferred) chunked upload endpoint.
 * 2. Call this endpoint to attach additional metadata such as image alt text.
 * 3. Create Tweet with media_id(s) attached.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-metadata-create)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [Media] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Media.createMetadata(
    mediaId: Long,
    payload: JsonObject,
    vararg options: Option
): EmptyApiAction = client.session.post("/1.1/media/metadata/create.json", EndpointHost.MediaUpload) {
    parameters(*options)
    buildJsonBody {
        payload.forEach { (k, v) ->
            put(k, v)
        }
        put("media_id", mediaId.toString())
    }
}.empty()
