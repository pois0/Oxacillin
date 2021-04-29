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

package jp.pois.oxacillin.defaults.models.entities

import jp.pois.oxacillin.extensions.IndexedEntityModel
import jp.pois.oxacillin.extensions.MessageData
import jp.pois.oxacillin.extensions.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class StatusEntity(
    public val hashtags: List<HashtagEntity>,
    public override val media: List<MediaEntity>,
    public val symbols: List<SymbolEntity>,
    @SerialName("user_mentions") public val userMentions: List<UserMentionEntity>,
    public override val urls: List<UrlEntity>
): Status.StatusEntity, MessageData.Entities {
    @Serializable
    public data class HashtagEntity(
        public val text: String,
        public override val indices: IntArray
    ): IndexedEntityModel {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as HashtagEntity

            if (text != other.text) return false
            if (!indices.contentEquals(other.indices)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = text.hashCode()
            result = 31 * result + indices.contentHashCode()
            return result
        }
    }

    @Serializable
    public data class UserMentionEntity(
        @SerialName("screen_name") public val screenName: String,
        public val name: String,
        public val id: Long,
        @SerialName("id_str") public val idStr: String,
        public override val indices: IntArray
    ): IndexedEntityModel {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as UserMentionEntity

            if (screenName != other.screenName) return false
            if (name != other.name) return false
            if (id != other.id) return false
            if (idStr != other.idStr) return false
            if (!indices.contentEquals(other.indices)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = screenName.hashCode()
            result = 31 * result + name.hashCode()
            result = 31 * result + id.hashCode()
            result = 31 * result + idStr.hashCode()
            result = 31 * result + indices.contentHashCode()
            return result
        }
    }

    @Serializable
    public data class SymbolEntity(
        public val text: String,
        public override val indices: IntArray
    ): IndexedEntityModel {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as SymbolEntity

            if (text != other.text) return false
            if (!indices.contentEquals(other.indices)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = text.hashCode()
            result = 31 * result + indices.contentHashCode()
            return result
        }
    }
}
