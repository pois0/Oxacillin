package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ExtendedProfile(
    public val id: Long,
    public val idStr: String,
    public val birthdate: Birthdate? = null
) {
    @Serializable
    public data class Birthdate(
        public val year: Int? = null,
        public val month: Int? = null,
        public val day: Int? = null,
        public val visibility: String,
        @SerialName("year_visibility") public val yearVisibility: String
    )
}
