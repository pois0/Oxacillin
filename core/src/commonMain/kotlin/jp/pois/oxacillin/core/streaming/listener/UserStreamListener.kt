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

package jp.pois.oxacillin.core.streaming.listener

import jp.pois.oxacillin.core.streaming.handler.UserStreamHandler

/**
 * An event model interface for [UserStreamHandler].
 */
public interface UserStreamListener<STATUS, DM, FRIENDS, DELETE, SCRUB_GEO, STATUS_WITHHELD, USER_WITHHELD, EVENT, ESTATUS: EVENT, ELIST: EVENT, EUSER: EVENT, WARNING, LIMIT, DISCONNECT>: StreamListener {
    /**
     * Called when a status is received.
     */
    public suspend fun onStatus(status: STATUS) {}
    /**
     * Called when a direct message is received.
     */
    public suspend fun onDirectMessage(message: DM) {}

    /**
     * Called when any events are received.
     */
    public suspend fun onAnyEvent(event: EVENT) {}

    /* Status event */

    /**
     * Called when any status events are received.
     */
    public suspend fun onAnyStatusEvent(event: ESTATUS) {}

    /**
     * Called when a favorite event is received.
     */
    public suspend fun onFavorite(event: ESTATUS) {}
    /**
     * Called when an unfavorite event is received.
     */
    public suspend fun onUnfavorite(event: ESTATUS) {}
    /**
     * Called when a favorited-retweet event is received.
     */
    public suspend fun onFavoritedRetweet(event: ESTATUS) {}
    /**
     * Called when a retweeted-retweet event is received.
     */
    public suspend fun onRetweetedRetweet(event: ESTATUS) {}
    /**
     * Called when a quoted-tweet event is received.
     */
    public suspend fun onQuotedTweet(event: ESTATUS) {}

    /* List event */

    /**
     * Called when any list events are received.
     */
    public suspend fun onAnyListEvent(event: ELIST) {}

    /**
     * Called when a list-created event is received.
     */
    public suspend fun onListCreated(event: ELIST) {}
    /**
     * Called when a list-destroyed event is received.
     */
    public suspend fun onListDestroyed(event: ELIST) {}
    /**
     * Called when a list-updated event is received.
     */
    public suspend fun onListUpdated(event: ELIST) {}
    /**
     * Called when a list-member-added event is received.
     */
    public suspend fun onListMemberAdded(event: ELIST) {}
    /**
     * Called when a list-member-removed event is received.
     */
    public suspend fun onListMemberRemoved(event: ELIST) {}
    /**
     * Called when a list-user-subscribed event is received.
     */
    public suspend fun onListUserSubscribed(event: ELIST) {}
    /**
     * Called when a list-user-unsubscribed event is received.
     */
    public suspend fun onListUserUnsubscribed(event: ELIST) {}

    /* User event */

    /**
     * Called when any user events are received.
     */
    public suspend fun onAnyUserEvent(event: EUSER) {}

    /**
     * Called when a follow event is received.
     */
    public suspend fun onFollow(event: EUSER) {}
    /**
     * Called when an unfollow event is received.
     */
    public suspend fun onUnfollow(event: EUSER) {}
    /**
     * Called when a block event is received.
     */
    public suspend fun onBlock(event: EUSER) {}
    /**
     * Called when an unblock event is received.
     */
    public suspend fun onUnblock(event: EUSER) {}
    /**
     * Called when a mute event is received.
     */
    public suspend fun onMute(event: EUSER) {}
    /**
     * Called when an unmute event is received.
     */
    public suspend fun onUnmute(event: EUSER) {}
    /**
     * Called when a user-update event is received.
     */
    public suspend fun onUserUpdate(event: EUSER) {}

    /* Misc */

    /**
     * Called when a friends event is received.
     */
    public suspend fun onFriends(friends: FRIENDS) {}

    /**
     * Called when a delete event is received.
     */
    public suspend fun onDelete(delete: DELETE) {}
    /**
     * Called when a scrub-geo event is received.
     */
    public suspend fun onScrubGeo(scrubGeo: SCRUB_GEO) {}
    /**
     * Called when a status-withheld event is received.
     */
    public suspend fun onStatusWithheld(withheld: STATUS_WITHHELD) {}
    /**
     * Called when a user-withheld event is received.
     */
    public suspend fun onUserWithheld(withheld: USER_WITHHELD) {}
    /**
     * Called when a disconnect-message event is received.
     */
    public suspend fun onDisconnectMessage(disconnect: DISCONNECT) {}
    /**
     * Called when a warning event is received.
     */
    public suspend fun onWarning(warning: WARNING) {}
    /**
     * Called when a limit event is received.
     */
    public suspend fun onLimit(limit: LIMIT) {}
}
