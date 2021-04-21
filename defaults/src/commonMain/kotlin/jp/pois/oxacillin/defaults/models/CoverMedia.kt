@file:Suppress("MemberVisibilityCanBePrivate")

package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class CoverMedia(
    @SerialName("tweet_id") public override val tweetId: String,
    public override val type: String,
    @SerialName("media") public override val media: JsonObject,
    @SerialName("media_id") public override val mediaId: String,
    @SerialName("url") public override val mediaUrl: String,
    @SerialName("w") public override val mediaWidth: Int,
    @SerialName("h") public override val mediaHeight: Int,
    public override val render: Render
): CommonCoverMedia()


public abstract class CommonCoverMedia {
    public abstract val tweetId: String
    public abstract val type: String
    public abstract val media: JsonObject
    public abstract val mediaId: String
    public abstract val mediaUrl: String
    public abstract val mediaWidth: Int
    public abstract val mediaHeight: Int
    public abstract val render: Render

    @Serializable
    public class Render(
        public val crops: Map<String, FaceCoordinate>
    ) {
        public inline val renderCropSquare: FaceCoordinate
            get() = crops["square"]!!

        public inline val renderCropPortrait9to16: FaceCoordinate
            get() = crops["portrait_9_16"]!!
    }
}
