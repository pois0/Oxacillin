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
): UrlEntityModel