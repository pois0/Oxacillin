package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
public data class Place(
    public val attributes: Attribute,
    @SerialName("bounding_box") public val boundingBox: BoundingBox,
    public val centroid: FloatArray,
    @SerialName("contained_within") public val containedWithin: List<Place>,
    public val country: String,
    @SerialName("country_code") public val countryCode: String,
    @SerialName("full_name") public val fullName: String,
    public val geometry: String? = null,
    public val id: String,
    public val name: String,
    @SerialName("place_type") public val placeType: String,
    public val polylines: JsonArray,
    public val url: String
) {
    @Serializable
    public data class Attribute(
        @SerialName("street_address") public val streetAddress: String? = null,
        public val locality: String? = null,
        public val region: String? = null,
        public val iso3: String? = null,
        @SerialName("postal_code") public val postalCode: String?,
        public val phone: String? = null,
        public val twitter: String? = null,
        public val url: String? = null,
        @SerialName("app_id") public val appId: String? = null,
        public val geotagCount: String?
    )

    @Serializable
    public data class BoundingBox(
        public val type: String,
        public val coordinates: List<List<LongArray>>
    )

    override fun hashCode(): Int = id.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Place

        if (attributes != other.attributes) return false
        if (boundingBox != other.boundingBox) return false
        if (!centroid.contentEquals(other.centroid)) return false
        if (containedWithin != other.containedWithin) return false
        if (country != other.country) return false
        if (countryCode != other.countryCode) return false
        if (fullName != other.fullName) return false
        if (geometry != other.geometry) return false
        if (id != other.id) return false
        if (name != other.name) return false
        if (placeType != other.placeType) return false
        if (polylines != other.polylines) return false
        if (url != other.url) return false

        return true
    }
}
