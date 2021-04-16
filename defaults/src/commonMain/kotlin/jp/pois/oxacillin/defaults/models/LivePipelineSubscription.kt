package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class LivePipelineSubscription(
    public val subscriptions: JsonObject
)