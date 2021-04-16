package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.extensions.Search
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Search(
    @SerialName("search_metadata") public override val searchMetadata: SearchMetadata,
    public val statuses: List<Status>
): Search {
    @Serializable
    public data class SearchMetadata(
        @SerialName("completed_in") public val completedIn: Float,
        public val count: Int,
        @SerialName("max_id") public val maxId: Long,
        @SerialName("max_id_str") public val maxIdStr: String,
        @SerialName("next_results") public override val nextResults: String?,
        public val query: String,
        @SerialName("refresh_url") public override val refreshUrl: String? = null,
        @SerialName("since_id") public val sinceId: Int,
        @SerialName("since_id_str") public val sinceIdStr: String
    ): Search.Metadata
}
