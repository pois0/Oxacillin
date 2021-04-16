package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SearchTypeahead(
    @SerialName("completed_in") public val completedIn: Float,
    public val hashtags: List<String>,
    @SerialName("num_results") public val numResults: Int,
    public val oneclick: List<String>,
    public val query: String,
    public val topics: List<Topic>,
    public val users: List<UserTypeahead>
) {
    @Serializable
    public data class Topic(
        public val inline: Boolean,
        @SerialName("rounded_score") public val roundedScore: Int,
        public val tokens: List<SearchToken>,
        public val topic: String
    )

    @Serializable
    public data class UserTypeahead(
        @SerialName("can_media_tag") public val canMediaTag: Boolean,
        @SerialName("connecting_user_count") public val connectingUserCount: Int,
        @SerialName("connecting_user_ids") public val connectingUserIds: List<Long>,
        public val id: Long,
        @SerialName("id_str") public val idStr: String,
        public val inline: Boolean,
        @SerialName("is_blocked") public val isBlocked: Boolean,
        @SerialName("is_dm_able") public val isDmAble: Boolean,
        @SerialName("is_protected") public val isProtected: Boolean,
        public val location: String? = null,
        public val name: String,
        @SerialName("profile_image_url") public val profileImageUrl: String? = null,
        @SerialName("profile_image_url_https") public val profileImageUrlHttps: String? = null,
        @SerialName("rounded_graph_weight") public val roundedGraphWeight: Int,
        @SerialName("rounded_score") public val roundedScore: Int,
        @SerialName("screen_name") public val screenName: String,
        @SerialName("social_context") public val socialContext: SocialContext,
        @SerialName("social_proof") public val socialProof: Int,
        @SerialName("social_proofs_ordered") public val socialProofsOrdered: List<Int>,
        public val tokens: List<SearchToken>,
        public val verified: Boolean
    ) {
        @Serializable
        public data class SocialContext(
            public val following: Boolean,
            @SerialName("followed_by") public val followedBy: Boolean
        )
    }

    @Serializable
    public data class SearchToken(
        public val token: String
    )
}