package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


public class Subscription private constructor() {
    @Serializable
    public data class Count(
        @SerialName("account_name") public val accountName: String,
        @SerialName("subscription_count_all") public val subscriptionCountAll: String,
        @SerialName("subscription_count_direct_messages") public val subscriptionCountDirectMessages: String
    )

    @Serializable
    public data class List(
        public val environment: String,
        @SerialName("application_id") public val applicationId: String,
        public val subscriptions: kotlin.collections.List<Subscription>
    ) {
        @Serializable
        public data class Subscription(
            @SerialName("user_id") public val userId: String
        )
    }
}