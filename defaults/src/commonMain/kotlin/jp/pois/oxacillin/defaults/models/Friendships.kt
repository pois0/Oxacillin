package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public class Friendships private constructor(){
    @Serializable
    public data class Show(
        public val relationship: Relationship
    )

    @Serializable
    public data class Lookup(
        public val connections: List<String>,
        public val id: Long,
        @SerialName("id_str") public val idStr: String,
        public val name: String,
        @SerialName("screen_name") public val screenName: String
    )
}