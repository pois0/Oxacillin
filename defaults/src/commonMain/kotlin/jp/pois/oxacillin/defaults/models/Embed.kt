package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Embed(
    @SerialName("author_name") public val authorName: String,
    @SerialName("author_url") public val authorUrl: String,
    @SerialName("cache_age") public val cacheAge: String,
    public val height: Int? = null,
    public val html: String,
    @SerialName("provider_name") public val providerName: String,
    @SerialName("provider_url") public val providerUrl: String,
    public val type: String,
    public val url: String,
    public val version: String,
    public val width: Int? = null
)
