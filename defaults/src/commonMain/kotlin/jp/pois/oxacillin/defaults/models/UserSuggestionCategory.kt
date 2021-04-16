package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable

@Serializable
public data class UserSuggestionCategory(
    public val name: String,
    public val size: Int,
    public val slug: String
)