package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class OAuthToken(
    @SerialName("token_type") public val tokenType: String? = null,
    @SerialName("access_token") public val accessToken: String
)