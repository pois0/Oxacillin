package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable

@Serializable
public data class Photo(
    public val large: Size,
    public val medium: Size,
    public val small: Size,
    public val thumb: Size
) {
    @Serializable
    public data class Size(
        public val h: Int,
        public val resize: String,
        public val w: Int
    )
}
