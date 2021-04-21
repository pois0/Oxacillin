package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Card(
    @SerialName("card_uri") public val cardUri: String,
    public val status: String
)
