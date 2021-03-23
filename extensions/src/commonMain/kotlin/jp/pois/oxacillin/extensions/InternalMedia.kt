package jp.pois.oxacillin.extensions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class InternalMedia(
    @SerialName("processing_info") val processingInfo: jp.pois.oxacillin.extensions.InternalMedia.ProcessingInfo?,
    @SerialName("media_id") override val mediaId: Long,
    @SerialName("media_key") override val mediaKey: String?
): jp.pois.oxacillin.extensions.Media {
    @Serializable
    internal class ProcessingInfo(
        @SerialName("check_after_secs") val checkAfterSecs: Int?,
        val state: jp.pois.oxacillin.extensions.InternalMedia.ProcessingInfo.State,
        val error: jp.pois.oxacillin.extensions.MediaError
    ) {
        @Serializable
        internal enum class State {
            @SerialName("pending") Pending,
            @SerialName("in_progress") InProgress,
            @SerialName("failed") Failed,
            @SerialName("succeeded") Succeeded
        }
    }
}

@Serializable
public data class MediaError(
    public val code: Int,
    public val name: String?,
    public val message: String
)