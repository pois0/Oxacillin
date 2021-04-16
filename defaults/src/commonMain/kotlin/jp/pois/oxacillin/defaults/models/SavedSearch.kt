package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("EqualsOrHashCode")
@Serializable
public data class SavedSearch(
    @SerialName("created_at") public val createdAt: String,
    public val id: Long,
    @SerialName("id_str") public val idStr: String,
    public val name: String,
    public val position: String,
    public val query: String
) {
    override fun hashCode(): Int = id.hashCode()
}
