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
import jp.pois.oxacillin.core.streaming.handler.UserStreamHandler
import jp.pois.oxacillin.core.streaming.listener.UserStreamListener
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Stream
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns realtime timeline.
 * 
 * @param options Optional. Custom parameters of this request.
 * @receiver [Stream] endpoint instance.
 * @return [StreamApiAction] for [UserStreamHandler] handler with [UserStreamListener] listener.
 */
@Suppress("SpellCheckingInspection")
@Deprecated("UserStream API retired on August 23th, 2018.", replaceWith = ReplaceWith("Tweetstorm or Account Activity API (AAA)"))
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
> {
    val request = client.session.get("/1.1/user.json", EndpointHost.UserStream) {
        parameters(
            "delimited" to delimited,
            "stall_warning" to stallWarnings,
            "with" to with,
            "replies" to replies,
            "track" to track?.joinToString(","),
            "filter_level" to filterLevel,
            "language" to language,
            "follow" to follow?.joinToString(","),
            "locations" to locations?.joinToString(",") { "${it.first},${it.second}" },
            "count" to count,
            "include_followings_activity" to includeFollowingsActivity,
            "stringify_friend_ids" to stringifyFriendIds,
            *options
        )
    }

    return UserStreamApiAction(client, request, statusDeserializer, dmDeserializer, friendsDeserializer, deleteDeserializer, scrubGeoDeserializer, statusWithheldDeserializer, userWithheldDeserializer, statusEventDeserializer, listEventDeserializer, userEventDeserializer, warningDeserializer, limitDeserializer, disconnectDeserializer)
}

private class UserStreamApiAction<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS: EVENT, ELIST: EVENT, EUSER: EVENT, WARNING, LIMIT, DISCONNECT>(
    client: ApiClient,
    request: ApiRequest,
    private val statusDeserializer: DeserializationStrategy<STATUS>,
    private val dmDeserializer: DeserializationStrategy<DM>,
    private val friendsDeserializer: DeserializationStrategy<FRIENDS>,
    private val deleteDeserializer: DeserializationStrategy<DELETE>,
    private val scrubGeoDeserializer: DeserializationStrategy<SCRUB_GEO>,
    private val statusWithheldDeserializer: DeserializationStrategy<STATUS_WITHHELD>,
    private val userWithheldDeserializer: DeserializationStrategy<USER_WITHHELD>,
    private val statusEventDeserializer: DeserializationStrategy<ESTATUS>,
    private val listEventDeserializer: DeserializationStrategy<ELIST>,
    private val userEventDeserializer: DeserializationStrategy<EUSER>,
    private val warningDeserializer: DeserializationStrategy<WARNING>,
    private val limitDeserializer: DeserializationStrategy<LIMIT>,
    private val disconnectDeserializer: DeserializationStrategy<DISCONNECT>
): StreamApiAction<UserStreamListener<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT>, UserStreamHandler<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT>>(client, request) {
    override fun getHandler(listener: UserStreamListener<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT>): UserStreamHandler<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT> = UserStreamHandler(client, listener, statusDeserializer, dmDeserializer, friendsDeserializer, deleteDeserializer, scrubGeoDeserializer, statusWithheldDeserializer, userWithheldDeserializer, statusEventDeserializer, listEventDeserializer, userEventDeserializer, warningDeserializer, limitDeserializer, disconnectDeserializer)
}
