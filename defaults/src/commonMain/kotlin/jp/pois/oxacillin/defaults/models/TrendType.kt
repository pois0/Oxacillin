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

package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
public data class TrendType(
    public val trend: Trend? = null,
    public val promotedTrend: JsonObject? = null
) {
    @Serializable
    public data class Trend(
        public val name: String,
        @SerialName("meta_description") public val metaDescription: String? = null,
        public val rank: Int,
        public val token: String,
        public val context: Context?,
        public val target: Target?
    ) {
        @Serializable
        public data class Context(
            public val query: List<String>
        )

        @Serializable
        public data class Target(
            public val query: String,
            @SerialName("pinned_tweets") public val pinnedTweets: LongArray,
            @SerialName("pinned_tweets_str") public val PinnedTweetsStr: List<String>
        ) {
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other == null || this::class != other::class) return false

                other as Target

                if (query != other.query) return false
                if (!pinnedTweets.contentEquals(other.pinnedTweets)) return false
                if (PinnedTweetsStr != other.PinnedTweetsStr) return false

                return true
            }

            override fun hashCode(): Int = query.hashCode()
        }
    }
}
