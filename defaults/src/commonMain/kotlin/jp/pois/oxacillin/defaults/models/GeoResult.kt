package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class GeoResult(
    public val query: Query,
    private val result: Result
) {
    @Serializable
    public data class Query(
        public val params: JsonObject,
        public val type: String,
        public val url:String
    )

    @Serializable
    public data class Result(
        public val places: List<Place>
    )
}
