package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class Moment(
    @SerialName("moment") public val content: Content
) {
    @Serializable
    public data class Content(
        public val id: String,
        public val title: String,
        public val description: String,
        public val url: String,
        @SerialName("is_live") public val isLive: Boolean,
        @SerialName("time_string") public val time: String,
        @SerialName("last_publish_time") public val lastPublishTime: String,
        @SerialName("subcategory_string") public val subcategory: String,
        public val sensitive: Boolean,
        @SerialName("duration_string") public val duration: String,
        @SerialName("can_subscribe") public val canSubscribe: Boolean,
        @SerialName("capsule_contents_version") public val capsuleContentsVersion: String,
        @SerialName("total_likes") public val totalLikes: Int,
        @SerialName("users") public val users: Map<String, User>,
        @SerialName("cover_media") public val coverMedia: CoverMedia,
        @SerialName("display_style") public val displayStyle: String,
        public val context: JsonObject,
        public val contextScribeInfo: ContextScribeInfo,
        @SerialName("tweets_map") public val tweets: Map<String, Status>,
        @SerialName("cover_format") public val coverFormat: CoverFormat,
        @SerialName("large_format") public val largeFormat: CoverFormat,
        @SerialName("thumbnail_format") public val thumbnailFormat: CoverFormat
    ) {
        @Serializable
        public data class ContextScribeInfo(
            public val momentPosition: String
        )

        @Serializable
        public data class CoverFormat(
            @SerialName("page_id") public val pageId: String,
            @SerialName("is_promoted") public val isPromoted: Boolean,
            @SerialName("link_title_card") public val linkTitleCard: JsonObject,
            @SerialName("link_url") public val linkUrl: String,
            @SerialName("display_url") public val displayUrl: String,
            @SerialName("vanity_source") public val VanitySource: String,
            public val title: String
        )
    }
}
