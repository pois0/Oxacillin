package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
public data class ApplicationRateLimitStatus(
    @SerialName("rate_limit_content") public val content: JsonObject,
    @SerialName("access_token") public val accessToken: String? = null,
    public val application: String? = null,
    public val resources: JsonElement
)
