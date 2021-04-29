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

public class WelcomeMessage private constructor() {
    @Serializable
    public data class Model(
        public val id: String,
        @SerialName("created_timestamp") public val createdTimestamp: String,
        @SerialName("message_data") public val messageData: Data
    ) {
        @Serializable
        public data class Data(
            public val text: String,
            public val attachment: JsonObject
        )
    }

    @Serializable
    public data class Single(
        @SerialName("welcome_message") public val welcomeMessage: Model,
        public val name: String? = null
    )

    @Serializable
    public data class List(
        @SerialName("welcome_messages") public val welcomeMessages: kotlin.collections.List<Model>,
        public val name: String? = null,
        @SerialName("next_cursor") public val nextCursor: String
    )
}
