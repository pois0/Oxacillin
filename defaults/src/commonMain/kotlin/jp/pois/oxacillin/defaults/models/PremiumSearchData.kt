package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.extensions.PremiumSearchModel
import kotlinx.serialization.Serializable

@Serializable
public data class PremiumSearchData(
    public val results: List<Status>,
    public override val next: String,
    public val requestParameters: RequestParameters
): PremiumSearchModel {
    @Serializable
    public data class RequestParameters(
        public val maxResults: Int,
        public val fromDateRaw: String,
        public val toDate: String
    )
}
