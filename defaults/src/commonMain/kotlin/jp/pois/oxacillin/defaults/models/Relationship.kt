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
