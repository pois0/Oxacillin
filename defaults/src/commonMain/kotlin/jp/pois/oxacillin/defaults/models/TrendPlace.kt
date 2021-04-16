package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

public data class TrendPlace(
    @SerialName("as_of") public val asOf: String,
    @SerialName("created_at") public val createdAt: String,
    public val locations: List<Location>,
    public val trends: List<Trend>
) {
    @Serializable
    public data class Location(
        public val name: String,
        public val woeid: Int
    )

    @Serializable
    public data class Trend(
        public val name: String,
        public val url: String,
        public val promotedContent: JsonObject? = null,
        public val query: String,
        public val tweetVolume: Int? = null
    )
}