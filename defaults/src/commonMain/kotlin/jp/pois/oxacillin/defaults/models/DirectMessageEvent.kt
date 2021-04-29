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

import jp.pois.oxacillin.extensions.IndexedEntityModel
import jp.pois.oxacillin.extensions.models.UrlEntityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

public class DirectMessageEvent private constructor() {
    @Serializable
    public data class List(
        public val apps: LinkedHashMap<String, App>,
        public val events: kotlin.collections.List<Event>,
        public val nextCursor: String? = null
    ) {
        @Serializable
        public data class App(
            public val id: String,
            public val name: String,
            public val url: String
        )

        @Serializable
        public data class Event(
            @SerialName("created_timestamp") public val createdTimestamp: String,
            public val id: String,
            @SerialName("message_create") public val messageCreate: MessageCreate,
            public val type: String
        ) {
            @Serializable
            public data class MessageCreate(
                @SerialName("message_data") public val messageData: MessageData,
                @SerialName("sender_id") public val senderId: String,
                @SerialName("source_app_id") public val sourceAppId: String? = null,
                public val text: String
            ) {
                @Serializable
                public data class MessageData(
                    public override val entities: Entities,
                    public override val text: String
                ): jp.pois.oxacillin.extensions.MessageData {
                    @Serializable
                    public data class Entities(
                        public val hashtags: kotlin.collections.List<Hashtag>,
                        public val symbols: JsonArray,
                        public override val urls: kotlin.collections.List<Url>,
                        @SerialName("user_mentions") public val userMentions: JsonArray
                    ): jp.pois.oxacillin.extensions.MessageData.Entities {
                        @Serializable
                        public data class Hashtag(
                            public override val indices: IntArray,
                            public val text: String
                        ): IndexedEntityModel {
                            override fun equals(other: Any?): Boolean {
                                if (this === other) return true
                                if (other == null || this::class != other::class) return false

                                other as Hashtag

                                if (!indices.contentEquals(other.indices)) return false
                                if (text != other.text) return false

                                return true
                            }

                            override fun hashCode(): Int {
                                var result = indices.contentHashCode()
                                result = 31 * result + text.hashCode()
                                return result
                            }
                        }

                        @Serializable
                        public data class Url(
                            @SerialName("display_url") public val displayUrl: String,
                            @SerialName("expanded_url") public override val expandedUrl: String,
                            public override val indices: IntArray,
                            public override val url: String
                        ): UrlEntityModel {
                            override fun equals(other: Any?): Boolean {
                                if (this === other) return true
                                if (other == null || this::class != other::class) return false

                                other as Url

                                if (displayUrl != other.displayUrl) return false
                                if (expandedUrl != other.expandedUrl) return false
                                if (!indices.contentEquals(other.indices)) return false
                                if (url != other.url) return false

                                return true
                            }

                            override fun hashCode(): Int {
                                var result = displayUrl.hashCode()
                                result = 31 * result + expandedUrl.hashCode()
                                result = 31 * result + indices.contentHashCode()
                                result = 31 * result + url.hashCode()
                                return result
                            }
                        }
                    }
                }
            }
        }
    }

    @Serializable
    public data class Show(
        public val event: List.Event
    )
}
