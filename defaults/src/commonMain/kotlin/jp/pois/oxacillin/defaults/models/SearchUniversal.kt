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

@Serializable
public data class SearchUniversal(
    public val metadata: Metadata,
    public val modules: List<Module>
) {
    @Serializable
    public data class Metadata(
        public val cursor: String,
        @SerialName("refresh_interval_in_sec") public val refreshIntervalInSec: Int
    )

    @Serializable
    public data class Module(
        public val status: Status? = null,
        @SerialName("user_gallery") public val userGallery: UserGallery? = null
    ) {
        @Serializable
        public data class Status(
            public val metadata: Metadata,
            public val data: jp.pois.oxacillin.defaults.models.Status
        ) {
            @Serializable
            public data class Metadata(
                @SerialName("result_type") public val resultType: String
            )
        }

        @Serializable
        public data class UserGallery(
            public val metadata: Metadata,
            public val data: Data
        ) {
            @Serializable
            public data class Metadata(
                @SerialName("result_type") public val resultType: String
            )

            @Serializable
            public data class Data(
                public val metadata: Metadata,
                public val data: User
            ) {
                @Serializable
                public data class Metadata(
                    @SerialName("result_type") public val resultType: String
                )
            }
        }
    }
}
