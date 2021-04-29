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

package jp.pois.oxacillin.defaults.endpoints.stream

import jp.pois.oxacillin.core.request.action.StreamApiAction
import jp.pois.oxacillin.core.streaming.handler.SampleStreamHandler
import jp.pois.oxacillin.core.streaming.listener.SampleStreamListener
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.defaults.endpoints.Stream
import jp.pois.oxacillin.endpoints.stream
import jp.pois.oxacillin.endpoints.stream.StreamDelimitedBy
import jp.pois.oxacillin.endpoints.stream.sample
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
): StreamApiAction<SampleStreamListener<STATUS, DELETE, WARNING>, SampleStreamHandler<STATUS, DELETE, WARNING>>
= client.stream.sample(statusDeserializer, deleteDeserializer, warningDeserializer, delimited, stallWarnings, language, *options)
