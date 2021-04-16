package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PinTweet(
    @SerialName("pinned_tweets") public val pinnedTweets: LongArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as PinTweet

        if (!pinnedTweets.contentEquals(other.pinnedTweets)) return false

        return true
    }

    override fun hashCode(): Int {
        return pinnedTweets.contentHashCode()
    }
}
