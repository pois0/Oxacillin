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

import jp.pois.oxacillin.extensions.exceptions.PenicillinTwitterMediaProcessingFailedError
import jp.pois.oxacillin.core.request.action.ApiAction
import jp.pois.oxacillin.core.response.JsonGeneralResponse
import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Statuses
import jp.pois.oxacillin.endpoints.media
import jp.pois.oxacillin.endpoints.media.MediaComponent
import jp.pois.oxacillin.endpoints.media.uploadStatus
import jp.pois.oxacillin.endpoints.statuses.create
import jp.pois.oxacillin.extensions.InternalMedia
import jp.pois.oxacillin.extensions.utils.deserializer
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.DeserializationStrategy
import kotlin.time.Duration
import kotlin.time.seconds

/**
 * Creates new tweet with media.
 *
 * @param status Tweet text.
 * @param media A list of media.
 * @param options Optional. Custom parameters of this request.
 */
public fun <T> Statuses.createWithMedia(
    deserializer: DeserializationStrategy<T>,
    status: String,
    media: List<MediaComponent>,
    vararg options: Option
): ApiAction<JsonGeneralResponse<T>> = delegatedAction {
    val results = media.map {
        client.media.uploadMedia<jp.pois.oxacillin.extensions.InternalMedia>(it).execute().awaitProcessing(client)
    }
    
    create(deserializer, status, mediaIds = results.map { it.mediaId }, options = options).execute()
}

public inline fun <reified T> Statuses.createWithMedia(
    status: String,
    media: List<MediaComponent>,
    vararg options: Option
): ApiAction<JsonGeneralResponse<T>> = createWithMedia(deserializer(), status, media, *options)

private val mediaProcessTimeout = 60.seconds
private val defaultCheckAfter = 5.seconds

/**
 * Awaits until media processing is done, and returns [InternalMedia] response.
 * This operation is suspendable.
 *
 * @param timeout Timeout value.
 */
private suspend fun jp.pois.oxacillin.extensions.InternalMedia.awaitProcessing(client: ApiClient, timeout: Duration? = null): jp.pois.oxacillin.extensions.InternalMedia {
    if (processingInfo == null) {
        return this
    }

    
    var result = this
    
    withTimeout(timeout ?: mediaProcessTimeout) {
        while (true) {
            delay(result.processingInfo?.checkAfterSecs?.seconds ?: defaultCheckAfter)

            val response = client.media.uploadStatus<jp.pois.oxacillin.extensions.InternalMedia>(mediaId, mediaKey).execute()
            result = response.result

            if (result.processingInfo?.error != null && result.processingInfo?.state == jp.pois.oxacillin.extensions.InternalMedia.ProcessingInfo.State.Failed) {
                throw PenicillinTwitterMediaProcessingFailedError(result.processingInfo?.error!!, response.request, response.response)
            }

            if (result.processingInfo == null || result.processingInfo?.state == jp.pois.oxacillin.extensions.InternalMedia.ProcessingInfo.State.Succeeded) {
                break
            }
        }
    }
    
    return result
}
