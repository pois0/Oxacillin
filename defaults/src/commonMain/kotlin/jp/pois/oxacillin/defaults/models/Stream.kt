package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public class Stream private constructor() {
    @Serializable
    public abstract class Event {
        public abstract val event: String
        public abstract val source: User
        public abstract val target: User
        public abstract val createdAt: String
    }

    @Serializable
    public data class UserEvent(
        public override val event: String,
        public override val source: User,
        public override val target: User,
        @SerialName("created_at") public override val createdAt: String
    ): Event()

    @Serializable
    public data class StatusEvent(
        public override val event: String,
        public override val source: User,
        public override val target: User,
        @SerialName("created_at") public override val createdAt: String,
        @SerialName("target_object") public val targetObject: Status
    ): Event()

    @Serializable
    public data class ListEvent(
        public override val event: String,
        public override val source: User,
        public override val target: User,
        @SerialName("created_at") public override val createdAt: String,
        @SerialName("target_object") public val targetObject: TwitterList
    ): Event()

    @Serializable
    public data class Friends(
        public val friends: List<Long?>,
        public val friendsStr: List<String?>
    )

    @Serializable
    public data class ScrubGeo(
        @SerialName("user_id") public val userId: Long,
        @SerialName("user_id_str") public val userIdStr: String,
        @SerialName("up_to_status_id") public val upToStatusId: Long,
        @SerialName("up_to_status_id_str") public val upToStatusIdStr: String,
        @SerialName("timestamp_ms") public val timestampMs: String
    )

    @Serializable
    public data class StatusWithheld(
        @SerialName("user_id") public val userId: Long,
        public val id: Long,
        @SerialName("timestamp_ms") public val timestampMs: String,
        @SerialName("withheld_in_countries") public val withheldInCountries: List<String>
    )

    @Serializable
    public data class UserWithheld(
        public val id: Long,
        @SerialName("withheld_in_countries") public val withheldInCountries: List<String>
    )

    @Serializable
    public data class Disconnect(
        public val code: Int,
        @SerialName("stream_name") public val streamName: String? = null,
        public val reason: String
    )

    @Serializable
    public data class Limit(
        public val track: Int,
        @SerialName("timestamp_ms") public val timestampMs: String
    )

    @Serializable
    public data class Warning(
        public val code: Int,
        public val message: String,
        @SerialName("percent_full") public val percentFull: Int? = null,
        public val userId: Long? = null
    )

    @Serializable
    public data class Delete(
        public val status: Status,
        @SerialName("timestamp_ms") public val timestampMs: String
    ) {
        @Serializable
        public data class Status(
            public val id: Long,
            @SerialName("id_str") public val idStr: String,
            public val userId: Long,
            @SerialName("user_id_str") public val userIdStr: String
        )
    }

    @Serializable
    public data class LivePipeline(
        public val topic: String,
        public val payload: Payload
    ) {
        @Serializable
        public data class Payload(
            @SerialName("tweet_engagement") public val tweetEngagement: TweetEngagement
        ) {
            @Serializable
            public data class TweetEngagement(
                @SerialName("like_count") public val likeCount: Int? = null,
                @SerialName("retweet_count") public val retweetCount: Int? = null,
                @SerialName("reply_count") public val replyCount: Int? = null
            )
        }
    }
}