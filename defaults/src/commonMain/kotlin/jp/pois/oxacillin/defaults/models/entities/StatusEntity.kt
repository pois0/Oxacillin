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