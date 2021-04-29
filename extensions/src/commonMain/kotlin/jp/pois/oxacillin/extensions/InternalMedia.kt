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

package jp.pois.oxacillin.extensions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class InternalMedia(
    @SerialName("processing_info") val processingInfo: ProcessingInfo?,
    @SerialName("media_id") override val mediaId: Long,
    @SerialName("media_key") override val mediaKey: String?
): Media {
    @Serializable
    internal class ProcessingInfo(
        @SerialName("check_after_secs") val checkAfterSecs: Int?,
        val state: State,
        val error: MediaError
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
