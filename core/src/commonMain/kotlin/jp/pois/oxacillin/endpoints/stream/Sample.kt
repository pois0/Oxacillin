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

package jp.pois.oxacillin.endpoints.stream


import jp.pois.oxacillin.core.request.ApiRequest
import jp.pois.oxacillin.core.request.EndpointHost
import jp.pois.oxacillin.core.request.action.StreamApiAction
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.core.streaming.handler.SampleStreamHandler
import jp.pois.oxacillin.core.streaming.listener.SampleStreamListener
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Stream
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns a small random sample of all public statuses via a stream. The Tweets returned by the default access level are the same, so if two different clients connect to this endpoint, they will see the same Tweets.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/sample-realtime/api-reference/get-statuses-sample)
 * 
 * @param delimited Specifies whether messages should be length-delimited. See [delimited](https://developer.twitter.com/en/docs/tweets/filter-realtime/guides/connecting) or more information.
 * @param stallWarnings Specifies whether stall warnings should be delivered. See [stall_warnings](https://developer.twitter.com/en/docs/tweets/filter-realtime/guides/connecting#stalls) for more information.
 * @param language Not documented yet.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Stream] endpoint instance.
 * @return [StreamApiAction] for [SampleStreamHandler] handler with [SampleStreamListener] listener.
 */
public fun <STATUS, DELETE, WARNING> Stream.sample(
    statusDeserializer: DeserializationStrategy<STATUS>,
    deleteDeserializer: DeserializationStrategy<DELETE>,
    warningDeserializer: DeserializationStrategy<WARNING>,
    delimited: StreamDelimitedBy = StreamDelimitedBy.Default,
    stallWarnings: Boolean? = null,
    language: String? = null,
    vararg options: Option
): StreamApiAction<SampleStreamListener<STATUS, DELETE, WARNING>, SampleStreamHandler<STATUS, DELETE, WARNING>> {
    val request = client.session.get("/1.1/statuses/sample.json", EndpointHost.Stream) {
        parameters(
            "delimited" to delimited,
            "stall_warning" to stallWarnings,
            "language" to language,
            *options
        )
    }

    return SampleStreamApiAction(client, request, statusDeserializer, deleteDeserializer, warningDeserializer)
}

private class SampleStreamApiAction<STATUS, DELETE, WARNING>(
    client: ApiClient,
    request: ApiRequest,
    private val statusDeserializer: DeserializationStrategy<STATUS>,
    private val deleteDeserializer: DeserializationStrategy<DELETE>,
    private val warningDeserializer: DeserializationStrategy<WARNING>
): StreamApiAction<SampleStreamListener<STATUS, DELETE, WARNING>, SampleStreamHandler<STATUS, DELETE, WARNING>>(client, request) {
    override fun getHandler(listener: SampleStreamListener<STATUS, DELETE, WARNING>): SampleStreamHandler<STATUS, DELETE, WARNING> = SampleStreamHandler(client, listener, statusDeserializer, deleteDeserializer, warningDeserializer)
}
