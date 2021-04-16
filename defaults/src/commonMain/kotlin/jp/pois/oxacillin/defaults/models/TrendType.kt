package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class TrendType(
    public val trend: Trend? = null,
    public val promotedTrend: JsonObject? = null
) {
    @Serializable
    public data class Trend(
        public val name: String,
        @SerialName("meta_description") public val metaDescription: String? = null,
        public val rank: Int,
        public val token: String,
        public val context: Context?,
        public val target: Target?
    ) {
        @Serializable
        public data class Context(
            public val query: List<String>
        )

        @Serializable
        public data class Target(
            public val query: String,
            @SerialName("pinned_tweets") public val pinnedTweets: LongArray,
            @SerialName("pinned_tweets_str") public val PinnedTweetsStr: List<String>
        ) {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || this::class != other::class) return false

                other as Target

                if (query != other.query) return false
                if (!pinnedTweets.contentEquals(other.pinnedTweets)) return false
                if (PinnedTweetsStr != other.PinnedTweetsStr) return false

                return true
            }

            override fun hashCode(): Int = query.hashCode()
        }
    }
}