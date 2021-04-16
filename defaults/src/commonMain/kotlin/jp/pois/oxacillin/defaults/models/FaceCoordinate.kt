package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.Serializable

@Serializable
public data class FaceCoordinate(
    public val x: Int,
    public val y: Int,
    public val h: Int,
    public val w: Int
)