package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.extensions.Media
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Media(
    @SerialName("expires_after_secs") public val expiresAfterSecs: Int? = null,
    @SerialName("media_id") public override val mediaId: Long,
    @SerialName("media_id_string") public val mediaIdString: String,
    @SerialName("media_key") public override val mediaKey: String?,
    @SerialName("processing_info") public val processingInfo: ProcessingInfo? = null,
    public val size: Int? = null,
    public val image: Image? = null,
    public val video: Video? = null
): Media {
    @Serializable
    public data class ProcessingInfo(
        @SerialName("check_after_secs") public val checkAfterSecs: Int? = null,
        public val error: Error? = null,
        @SerialName("progress_percent") public val progressPercent: Int? = null,
        public val state: State
    ) {
        @Serializable
        public data class Error(
            public val code: Int,
            public val name: String? = null,
            public val message: String
        )

        @Serializable
        public enum class State {
            @SerialName("pending") Pending,
            @SerialName("in_progress") InProgress,
            @SerialName("failed") Failed,
            @SerialName("succeeded") Succeeded
        }
    }

    @Serializable
    public data class Image(
        @SerialName("image_type") public val imageType: String,
        public val w: Int,
        public val h: Int
    )

    @Serializable
    public data class Video(
        @SerialName("video_type") public val videoType: String
    )

    override fun hashCode(): Int = mediaId.hashCode()
}
