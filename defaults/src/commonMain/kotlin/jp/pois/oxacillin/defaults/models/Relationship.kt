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

package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Relationship(
    public val source: Source,
    public val target: Target
) {
    @Serializable
    public data class Source(
        @SerialName("all_replies") public val allReplies: Boolean,
        @SerialName("blocked_by") public val blockedBy: Boolean,
        public val blocking: Boolean,
        @SerialName("can_dm") public val canDm: Boolean,
        @SerialName("can_media_tag") public val canMediaTag: Boolean,
        @SerialName("followed_by") public val followedBy: Boolean,
        public val following: Boolean,
        @SerialName("following_received") public val followingReceived: Boolean,
        @SerialName("following_requested") public val followingRequested: Boolean,
        public val id: Long,
        @SerialName("id_str") public val idStr: String,
        @SerialName("live_following") public val liveFollowing: Boolean,
        @SerialName("marked_spam") public val markedSpam: Boolean,
        public val muting: Boolean,
        @SerialName("notification_enabled") public val notificationEnabled: Boolean,
        @SerialName("screen_name") public val screenName: String,
        @SerialName("want_retweets") public val wantRetweets: Boolean
    )

    @Serializable
    public data class Target(
        @SerialName("followed_by") public val followedBy: Boolean,
        public val following: Boolean,
        @SerialName("following_received") public val followingReceived: Boolean,
        @SerialName("following_requested") public val followingRequested: Boolean,
        public val id: Long,
        @SerialName("id_str") public val idStr: String,
        @SerialName("screen_name") public val screenName: String
    )
}
