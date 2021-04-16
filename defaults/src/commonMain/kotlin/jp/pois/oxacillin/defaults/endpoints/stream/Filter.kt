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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.pois.oxacillin.defaults.endpoints.stream

import jp.pois.oxacillin.core.request.action.StreamApiAction
import jp.pois.oxacillin.core.streaming.handler.FilterStreamHandler
import jp.pois.oxacillin.core.streaming.listener.FilterStreamListener
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.defaults.endpoints.Stream
import jp.pois.oxacillin.endpoints.stream
import jp.pois.oxacillin.endpoints.stream.StreamDelimitedBy
import jp.pois.oxacillin.endpoints.stream.filter
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns public statuses that match one or more filter predicates. Multiple parameters may be specified which allows most clients to use a single connection to the Streaming API. Both GET and POST requests are supported, but GET requests with too many parameters may cause the request to be rejected for excessive URL length. Use a POST request to avoid long URLs.
 * The track, follow, and locations fields should be considered to be combined with an OR operator. track=foo&follow=1234 returns Tweets matching "foo" OR created by user 1234.
 * The default access level allows up to 400 track keywords, 5,000 follow userids and 25 0.1-360 degree location boxes. If you need access to more rules and filtering tools, please apply for [enterprise access](https://developer.twitter.com/en/enterprise).
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/filter-realtime/api-reference/post-statuses-filter)
 *
 * @param follow A comma separated list of user IDs, indicating the users to return statuses for in the stream. See [follow](https://developer.twitter.com/en/docs/tweets/filter-realtime/guides/basic-stream-parameters) for more information.
 * @param track Keywords to track. Phrases of keywords are specified by a comma-separated list. See [track](https://developer.twitter.com/en/docs/tweets/filter-realtime/guides/basic-stream-parameters) for more information.
 * @param locations Specifies a set of bounding boxes to track. See [locations](https://developer.twitter.com/en/docs/tweets/filter-realtime/guides/basic-stream-parameters) for more information.
 * @param delimited Specifies whether messages should be length-delimited. See [delimited](https://developer.twitter.com/en/docs/tweets/filter-realtime/guides/connecting) or more information.
 * @param stallWarnings Specifies whether stall warnings should be delivered. See [stall_warnings](https://developer.twitter.com/en/docs/tweets/filter-realtime/guides/connecting#stalls) for more information.
 * @param language Not documented yet.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Stream] endpoint instance.
 * @return [StreamApiAction] for [FilterStreamHandler] handler with [FilterStreamListener] listener.
 */
public inline fun <STATUS, DELETE, WARNING> Stream.filter(
    statusDeserializer: DeserializationStrategy<STATUS>,
    deleteDeserializer: DeserializationStrategy<DELETE>,
    warningDeserializer: DeserializationStrategy<WARNING>,
    follow: List<Long>? = null,
    track: List<String>? = null,
    locations: List<Pair<Double, Double>>? = null,
    delimited: StreamDelimitedBy = StreamDelimitedBy.Default,
    stallWarnings: Boolean? = null,
    language: String? = null,
    vararg options: Option
): StreamApiAction<FilterStreamListener<STATUS, DELETE, WARNING>, FilterStreamHandler<STATUS, DELETE, WARNING>>
        = client.stream.filter(statusDeserializer, deleteDeserializer, warningDeserializer, follow, track, locations, delimited, stallWarnings, language, *options)