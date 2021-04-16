package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


public class WelcomeMessageRule private constructor() {
    @Serializable
    public data class Model(
        public val id: String,
        @SerialName("created_timestamp") public val createdTimestamp: String,
        @SerialName("welcome_message_id") public val welcomeMessageId: String
    )

    @Serializable
    public data class Single(
        @SerialName("welcome_message_rule") public val welcomeMessageRule: Model
    )

    @Serializable
    public data class List(
        @SerialName("welcome_message_rules") public val welcomeMessageRules: kotlin.collections.List<Model>,
        @SerialName("next_cursor") public val nextCursor: String? = null
    )
}