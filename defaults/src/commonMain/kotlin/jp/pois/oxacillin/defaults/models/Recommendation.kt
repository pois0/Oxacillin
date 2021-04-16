package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable

@Serializable
public data class Recommendation(
    public val token: String,
    public val userId: String,
    public val user: User
)
