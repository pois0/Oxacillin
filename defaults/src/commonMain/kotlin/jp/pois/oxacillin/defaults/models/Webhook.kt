package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public class Webhook private constructor() {
    @Serializable
    public data class Model(
        public val id: String,
        public val url: String,
        public val valid: Boolean,
        @SerialName("created_at") public val createdAt: String
    )

    @Serializable
    public data class List(
        public val environments: kotlin.collections.List<Environment>
    ) {
        @Serializable
        public data class Environment(
            @SerialName("environment_name") public val name: String,
            public val webhook: kotlin.collections.List<Model>
        )
    }
}