package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MomentGuide(
    public val category: Category,
    @SerialName("impression_id") public val impressionId: Long,
    @SerialName("scroll_cursor") public val scrollCursor: String,
    public val modules: List<Module>,
    public val trendModule: TrendModule
) {
    @Serializable
    public data class Category(
        @SerialName("category_id") public val id: String,
        public val name: String,
        public val uri: String
    )

    @Serializable
    public data class Module(
        @SerialName("module_type") public val moduleType: String,
        public val moments: List<Moment>
    )

    @Serializable
    public data class TrendModule(
        public val metadata: TrendMetadata,
        public val trends: List<TrendType>
    )
}