/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
 *     Copyright (c) 2021 poispois
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
