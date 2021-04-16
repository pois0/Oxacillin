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
import jp.pois.oxacillin.core.streaming.handler.UserStreamHandler
import jp.pois.oxacillin.core.streaming.listener.UserStreamListener
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.defaults.endpoints.Stream
import jp.pois.oxacillin.endpoints.stream
import jp.pois.oxacillin.endpoints.stream.*
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns realtime timeline.
 * 
 * @param options Optional. Custom parameters of this request.
 * @receiver [Stream] endpoint instance.
 * @return [StreamApiAction] for [UserStreamHandler] handler with [UserStreamListener] listener.
 */
@Suppress("SpellCheckingInspection", "DEPRECATION")
@Deprecated("UserStream API retired on August 23th, 2018.", replaceWith = ReplaceWith("Account Activity API (AAA)"))
public fun <STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS: EVENT, ELIST: EVENT, EUSER: EVENT, WARNING, LIMIT, DISCONNECT> Stream.user(
    statusDeserializer: DeserializationStrategy<STATUS>,
    dmDeserializer: DeserializationStrategy<DM>,
    friendsDeserializer: DeserializationStrategy<FRIENDS>,
    deleteDeserializer: DeserializationStrategy<DELETE>,
    scrubGeoDeserializer: DeserializationStrategy<SCRUB_GEO>,
    statusWithheldDeserializer: DeserializationStrategy<STATUS_WITHHELD>,
    userWithheldDeserializer: DeserializationStrategy<USER_WITHHELD>,
    statusEventDeserializer: DeserializationStrategy<ESTATUS>,
    listEventDeserializer: DeserializationStrategy<ELIST>,
    userEventDeserializer: DeserializationStrategy<EUSER>,
    warningDeserializer: DeserializationStrategy<WARNING>,
    limitDeserializer: DeserializationStrategy<LIMIT>,
    disconnectDeserializer: DeserializationStrategy<DISCONNECT>,
    delimited: StreamDelimitedBy = StreamDelimitedBy.Default,
    stallWarnings: Boolean? = null,
    with: UserStreamWith = UserStreamWith.Default,
    replies: UserStreamReplies = UserStreamReplies.Default,
    track: List<String>? = null,
    filterLevel: UserStreamFilterLevel = UserStreamFilterLevel.Default,
    language: String? = null,
    follow: List<Long>? = null,
    locations: List<Pair<Double, Double>>? = null,
    count: Int? = null,
    includeFollowingsActivity: Boolean? = null,
    stringifyFriendIds: Boolean? = null,
    vararg options: Option
): StreamApiAction<UserStreamListener<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT>,
        UserStreamHandler<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT>
> = client.stream.user(statusDeserializer, dmDeserializer, friendsDeserializer, deleteDeserializer, scrubGeoDeserializer, statusWithheldDeserializer, userWithheldDeserializer, statusEventDeserializer, listEventDeserializer, userEventDeserializer, warningDeserializer, limitDeserializer, disconnectDeserializer, delimited, stallWarnings, with, replies, track, filterLevel, language, follow, locations, count, includeFollowingsActivity, stringifyFriendIds, *options)
