package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable

public data class TrendArea(
    public val country: String,
    public val countryCode: String,
    public val name: String,
    public val parentid: Int,
    public val placeType: PlaceType,
    public val url: String,
    public val woeid: Int
) {
    @Serializable
    public data class PlaceType(
        public val code: Int,
        public val name: String
    )
}