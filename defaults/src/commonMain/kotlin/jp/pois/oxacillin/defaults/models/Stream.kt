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
