package jp.pois.oxacillin.defaults.models.entities

import kotlinx.serialization.Serializable

@Serializable
public data class UserEntity(
    public val url: UserProfileEntity?,
    public val description: UserProfileEntity?
) {
    @Serializable
    public data class UserProfileEntity(
        public val urls: List<UrlEntity>
    )
}