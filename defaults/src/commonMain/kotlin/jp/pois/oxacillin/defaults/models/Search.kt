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
