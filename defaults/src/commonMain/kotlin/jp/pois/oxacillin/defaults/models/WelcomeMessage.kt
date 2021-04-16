package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

public class WelcomeMessage private constructor() {
    @Serializable
    public data class Model(
        public val id: String,
        @SerialName("created_timestamp") public val createdTimestamp: String,
        @SerialName("message_data") public val messageData: Data
    ) {
        @Serializable
        public data class Data(
            public val text: String,
            public val attachment: JsonObject
        )
    }

    @Serializable
    public data class Single(
        @SerialName("welcome_message") public val welcomeMessage: Model,
        public val name: String? = null
    )

    @Serializable
    public data class List(
        @SerialName("welcome_messages") public val welcomeMessages: kotlin.collections.List<Model>,
        public val name: String? = null,
        @SerialName("next_cursor") public val nextCursor: String
    )
}