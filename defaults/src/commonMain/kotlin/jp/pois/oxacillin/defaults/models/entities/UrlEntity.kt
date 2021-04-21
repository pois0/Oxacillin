package jp.pois.oxacillin.defaults.models.entities

import jp.pois.oxacillin.extensions.models.UrlEntityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UrlEntity(
    public override val url: String,
    @SerialName("expanded_url") public override val expandedUrl: String,
    @SerialName("display_url") public val displayUrl: String,
    public override val indices: IntArray
): UrlEntityModel {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as UrlEntity

        if (url != other.url) return false
        if (expandedUrl != other.expandedUrl) return false
        if (displayUrl != other.displayUrl) return false
        if (!indices.contentEquals(other.indices)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = url.hashCode()
        result = 31 * result + expandedUrl.hashCode()
        result = 31 * result + displayUrl.hashCode()
        result = 31 * result + indices.contentHashCode()
        return result
    }
}
