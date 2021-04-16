package jp.pois.oxacillin.defaults.models.entities

import jp.pois.oxacillin.defaults.models.FaceCoordinate
import jp.pois.oxacillin.defaults.models.Photo
import jp.pois.oxacillin.extensions.IndexedEntityModel
import jp.pois.oxacillin.extensions.models.UrlEntityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class MediaEntity(
    @SerialName("additional_media_info") public val additionalMediaInfo: AdditionalMediaInfo? = null,
    @SerialName("display_url") public val displayUrl: String,
    @SerialName("expanded_url") public override val expandedUrl: String,
    @SerialName("ext_alt_text") public val extAltText: String? = null,
    public val features: Feature? = null,
    public val id: Long,
    @SerialName("id_str") public val idStr: String,
    public override val indices: IntArray,
    @SerialName("media_url") public val mediaUrl: String,
    @SerialName("media_url_https") public val mediaUrlHttps: String,
    public val sizes: Photo? = null,
    @SerialName("source_status_id") public val sourceStatusId: Long? = null,
    @SerialName("source_status_id_str") public val sourceStatusIdStr: String? = null,
    public val type: String,
    public override val url: String,
    @SerialName("video_info") public val videoInfo: VideoInfo? = null
): UrlEntityModel {
    @Serializable
    public data class AdditionalMediaInfo(
        public val title: String,
        public val description: String,
        public val embeddable: Boolean
    )

    @Serializable
    public data class Feature(
        public val large: Size,
        public val medium: Size,
        public val orig: Size,
        public val small: Size
    ) {
        @Serializable
        public data class Size(
            public val faces: List<FaceCoordinate>
        )
    }

    @Serializable
    public data class VideoInfo(
        @SerialName("duration_millis") public val durationMillis: Int,
        public val aspectRatio: IntArray,
        public val variants: List<Variant>
    ) {
        @Serializable
        public data class Variant(
            public val bitrate: Int? = null,
            @SerialName("content_type") public val contentType: String,
            public val url: String
        )

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as VideoInfo

            if (durationMillis != other.durationMillis) return false
            if (!aspectRatio.contentEquals(other.aspectRatio)) return false
            if (variants != other.variants) return false

            return true
        }

        override fun hashCode(): Int {
            var result = durationMillis
            result = 31 * result + aspectRatio.contentHashCode()
            result = 31 * result + variants.hashCode()
            return result
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MediaEntity

        if (additionalMediaInfo != other.additionalMediaInfo) return false
        if (displayUrl != other.displayUrl) return false
        if (expandedUrl != other.expandedUrl) return false
        if (extAltText != other.extAltText) return false
        if (features != other.features) return false
        if (id != other.id) return false
        if (idStr != other.idStr) return false
        if (!indices.contentEquals(other.indices)) return false
        if (mediaUrl != other.mediaUrl) return false
        if (mediaUrlHttps != other.mediaUrlHttps) return false
        if (sizes != other.sizes) return false
        if (sourceStatusId != other.sourceStatusId) return false
        if (sourceStatusIdStr != other.sourceStatusIdStr) return false
        if (type != other.type) return false
        if (url != other.url) return false
        if (videoInfo != other.videoInfo) return false

        return true
    }

    override fun hashCode(): Int = id.hashCode()
}
