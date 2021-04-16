package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SearchUniversal(
    public val metadata: Metadata,
    public val modules: List<Module>
) {
    @Serializable
    public data class Metadata(
        public val cursor: String,
        @SerialName("refresh_interval_in_sec") public val refreshIntervalInSec: Int
    )

    @Serializable
    public data class Module(
        public val status: Status? = null,
        @SerialName("user_gallery") public val userGallery: UserGallery? = null
    ) {
        @Serializable
        public data class Status(
            public val metadata: Metadata,
            public val data: jp.pois.oxacillin.defaults.models.Status
        ) {
            @Serializable
            public data class Metadata(
                @SerialName("result_type") public val resultType: String
            )
        }

        @Serializable
        public data class UserGallery(
            public val metadata: Metadata,
            public val data: Data
        ) {
            @Serializable
            public data class Metadata(
                @SerialName("result_type") public val resultType: String
            )

            @Serializable
            public data class Data(
                public val metadata: Metadata,
                public val data: User
            ) {
                @Serializable
                public data class Metadata(
                    @SerialName("result_type") public val resultType: String
                )
            }
        }
    }
}
