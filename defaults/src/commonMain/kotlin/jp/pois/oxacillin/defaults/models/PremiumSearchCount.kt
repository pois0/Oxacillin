package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.extensions.PremiumSearchModel
import kotlinx.serialization.Serializable

@Serializable
public data class PremiumSearchCount(
    public val results: List<Count>,
    public val totalCount: Int,
    public override val next: String? = null,
    public val requestParameters: List<RequestParameters>
): PremiumSearchModel {
    @Serializable
    public data class Count(
        public val timePeriod: String,
        public val count: Int
    )

    @Serializable
    public data class RequestParameters(
        public val fromDate: String? = null,
        public val toDate: String? = null,
        public val bucketRaw: String? = null
    )
}
