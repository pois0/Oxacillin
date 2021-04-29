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

import jp.pois.oxacillin.extensions.CreatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ActivityEvent(
    public val action: String,
    @SerialName("max_position") public val maxPosition: String,
    @SerialName("min_position") public val minPosition: String,
    @SerialName("created_at") public val createdAt: CreatedAt,
    @SerialName("target_objects") public val targetObjects: List<Status>,
    @SerialName("target_objects_size") public val targetObjectsSize: Int,
    public val targets: List<User>,
    @SerialName("targets_size") public val targetsSize: Int,
    public val sources: List<User>,
    @SerialName("sources_size") public val sourcesSize: Int
)
