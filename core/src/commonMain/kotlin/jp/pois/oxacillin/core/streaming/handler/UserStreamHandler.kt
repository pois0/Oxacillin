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

package jp.pois.oxacillin.core.streaming.handler

import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.core.streaming.listener.UserStreamListener
import jp.pois.oxacillin.utils.myJson
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonObject

/**
 * Default UserStream [StreamHandler].
 * Accepts listener of [UserStreamListener].
 */
public class UserStreamHandler<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS: EVENT, ELIST: EVENT, EUSER: EVENT, WARNING, LIMIT, DISCONNECT>(
    override val client: ApiClient,
    override val listener: UserStreamListener<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT>,
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
): StreamHandler<UserStreamListener<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS, ELIST, EUSER, WARNING, LIMIT, DISCONNECT>> {
    override suspend fun handle(json: JsonObject) {
        when {
            "text" in json -> {
                listener.onStatus(myJson.decodeFromJsonElement(statusDeserializer, json))
            }
            "direct_message" in json -> {
                listener.onDirectMessage(myJson.decodeFromJsonElement(dmDeserializer, json))
            }
            "event" in json -> {
                val event = UserStreamEvent.byKey(json["event"]!!.toString())
                when (event?.type) {
                    UserStreamEventType.Status -> {
                        val statusEvent = myJson.decodeFromJsonElement(statusEventDeserializer, json)

                        when (event) {
                            UserStreamEvent.Favorite -> listener.onFavorite(statusEvent)
                            UserStreamEvent.Unfavorite -> listener.onUnfavorite(statusEvent)
                            UserStreamEvent.FavoritedRetweet -> listener.onFavoritedRetweet(statusEvent)
                            UserStreamEvent.RetweetedRetweet -> listener.onRetweetedRetweet(statusEvent)
                            UserStreamEvent.QuotedTweet -> listener.onQuotedTweet(statusEvent)
                            else -> listener.onUnhandledJson(json)
                        }

                        listener.onAnyStatusEvent(statusEvent)
                        listener.onAnyEvent(statusEvent)
                    }
                    UserStreamEventType.List -> {
                        val listEvent = myJson.decodeFromJsonElement(listEventDeserializer, json)

                        when (event) {
                            UserStreamEvent.ListCreated -> listener.onListCreated(listEvent)
                            UserStreamEvent.ListDestroyed -> listener.onListDestroyed(listEvent)
                            UserStreamEvent.ListUpdated -> listener.onListUpdated(listEvent)
                            UserStreamEvent.ListMemberAdded -> listener.onListMemberAdded(listEvent)
                            UserStreamEvent.ListMemberRemoved -> listener.onListMemberRemoved(listEvent)
                            UserStreamEvent.ListUserSubscribed -> listener.onListUserSubscribed(listEvent)
                            UserStreamEvent.ListUserUnsubscribed -> listener.onListUserUnsubscribed(listEvent)
                            else -> listener.onUnhandledJson(json)
                        }

                        listener.onAnyListEvent(listEvent)
                        listener.onAnyEvent(listEvent)
                    }
                    UserStreamEventType.User -> {
                        val userEvent = myJson.decodeFromJsonElement(userEventDeserializer, json)

                        when (event) {
                            UserStreamEvent.Follow -> listener.onFollow(userEvent)
                            UserStreamEvent.Unfollow -> listener.onUnfollow(userEvent)
                            UserStreamEvent.Block -> listener.onBlock(userEvent)
                            UserStreamEvent.Unblock -> listener.onUnblock(userEvent)
                            UserStreamEvent.Mute -> listener.onMute(userEvent)
                            UserStreamEvent.Unmute -> listener.onUnmute(userEvent)
                            UserStreamEvent.UserUpdate -> listener.onUserUpdate(userEvent)
                            else -> listener.onUnhandledJson(json)
                        }

                        listener.onAnyUserEvent(userEvent)
                        listener.onAnyEvent(userEvent)
                    }
                    else -> {
                        listener.onUnhandledJson(json)
                    }
                }
            }
            "friends" in json -> {
                listener.onFriends(myJson.decodeFromJsonElement(friendsDeserializer, json["friends"]!!))
            }
            "delete" in json -> {
                listener.onDelete(myJson.decodeFromJsonElement(deleteDeserializer, json["delete"]!!))
            }
            "scrub_geo" in json -> {
                listener.onScrubGeo(myJson.decodeFromJsonElement(scrubGeoDeserializer, json["scrub_geo"]!!))
            }
            "status_withheld" in json -> {
                listener.onStatusWithheld(myJson.decodeFromJsonElement(statusWithheldDeserializer, json["status_withheld"]!!))
            }
            "user_withheld" in json -> {
                listener.onUserWithheld(myJson.decodeFromJsonElement(userWithheldDeserializer, json["user_withheld"]!!))
            }
            "disconnect" in json -> {
                listener.onDisconnectMessage(myJson.decodeFromJsonElement(disconnectDeserializer, json["disconnect"]!!))
            }
            "warning" in json -> {
                listener.onWarning(myJson.decodeFromJsonElement(warningDeserializer, json["warning"]!!))
            }
            "limit" in json -> {
                listener.onLimit(myJson.decodeFromJsonElement(limitDeserializer, json["limit"]!!))
            }
            else -> {
                listener.onUnhandledJson(json)
            }
        }

        listener.onAnyJson(json)
    }
}
