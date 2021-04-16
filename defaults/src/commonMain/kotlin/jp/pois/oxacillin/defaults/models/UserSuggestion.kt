package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable

@Serializable
public data class UserSuggestion(
    public val name: String,
    public val size: Int,
    public val slug: String,
    public val users: List<User>
)