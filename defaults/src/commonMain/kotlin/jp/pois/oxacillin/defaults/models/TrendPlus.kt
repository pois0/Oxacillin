package jp.pois.oxacillin.defaults.models

public data class TrendPlus(
    public val id: Long,
    public val metadata: TrendMetadata,
    public val modules: List<TrendType>
)