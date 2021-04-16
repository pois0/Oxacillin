package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TrendMetadata(
    @SerialName("context_mode") public val contextMode: String,
    @SerialName("refresh_interval_millis") public val refreshIntervalMillis: Int,
    public val timestamp: Long
)