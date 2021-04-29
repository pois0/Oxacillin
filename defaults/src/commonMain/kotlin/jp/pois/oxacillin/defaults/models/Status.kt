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

import jp.pois.oxacillin.defaults.models.entities.StatusEntity
import jp.pois.oxacillin.extensions.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
public data class Status(
    @SerialName("created_at") public val createdAt: String,
    public val id: Long,
    @SerialName("id_str") public val idStr: String,

    @SerialName("text") public override val textRaw: String? = null,
    @SerialName("full_text") public override val fullTextRaw: String? = null,
    @SerialName("display_text_range") public val displayTextRange: List<Int>,

    public override val entities: StatusEntity,
    @SerialName("extended_entities") public val extendedEntities: StatusEntity?,

    public val truncated: Boolean,
    public val source: String,

    @SerialName("in_reply_to_screen_name") public val inReplyToScreenName: String? = null,
    @SerialName("in_reply_to_status_id") public val inReplyToStatusId: Long? = null,
    @SerialName("in_reply_to_status_id_str") public val inReplyToStatusIdStr: String? = null,
    @SerialName("in_reply_to_user_id") public val inReplyToUserId: Long? = null,
    @SerialName("in_reply_to_user_id_str") public val inReplyToUserIdStr: String? = null,

    public override val user: User,

    @Deprecated("geo field is deprecated. Use coordinates instead.", replaceWith = ReplaceWith("coordinates"))
    public val geo: JsonElement? = null,
    public val coordinates: Coordinate? = null,
    public val place: Place? = null,

    public val contributors: List<Contributor>,

    @SerialName("quoted_status_id") public val quotedStatusId: Long? = null,
    @SerialName("quoted_status_id_str") public val quotedStatusIdStr: String? = null,
    @SerialName("quoted_status") public val quotedStatus: Status? = null,
    @SerialName("is_quote_status") public val isQuoteStatus: Boolean,

    @SerialName("retweet_count") public val retweetCount: Int,
    @SerialName("favorite_count") public val favoriteCount: Int,
    @SerialName("quote_count") public val quoteCount: Int? = null,
    @SerialName("reply_count") public val replyCount: Int? = null,

    public val favorited: Boolean,
    public val retweeted: Boolean,

    @SerialName("possibly_sensitive") public val possiblySensitive: Boolean,
    @SerialName("possibly_sensitive_appealable") public val possiblySensitiveAppealable: Boolean? = null,
    @SerialName("possibly_sensitive_editable") public val possiblySensitiveEditable: Boolean? = null,

    public val langRaw: String,
    @SerialName("card_uri") public override val cardUri: String? = null,

    @SerialName("conversation_id") public val conversationId: Long? = null,
    @SerialName("current_user_retweet") public val currentUserRetweet: CurrentUserRetweet? = null,
    @SerialName("extended_tweet") public override val extendedTweet: ExtendedTweet? = null,
    @SerialName("filter_level") public val filterLevel: String? = null,
    @SerialName("retweeted_status") public override val retweetedStatus: Status? = null,
    @SerialName("supplemental_language") public val supplementalLanguage: String? = null,
    @SerialName("timestamp_ms") public val timestampMs: String? = null,
    @SerialName("withheld_copyright") public val withheldCopyright: Boolean? = null,
    @SerialName("withheld_in_countries") public val withheldInCountries: List<String>,
    @SerialName("withheld_scope") public val withheldScope: String? = null,
    @SerialName("matching_rules") public val matchingRules: List<MatchingRule?>
): Status {
    @Serializable
    public data class Contributor(
        public val id: Long,
        @SerialName("id_str") public val idStr: String,
        @SerialName("screen_name") public val screenName: String
    )

    @Serializable
    public data class Coordinate(
        public val coordinates: FloatArray,
        public val type: String,
    ) {
        public val longitude: Float?
            get() = coordinates.takeIf { it.size == 2 }?.get(0)

        public val latitude: Float?
            get() = coordinates.takeIf { it.size == 2 }?.get(0)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as Coordinate

            if (!coordinates.contentEquals(other.coordinates)) return false
            if (type != other.type) return false

            return true
        }

        override fun hashCode(): Int {
            var result = coordinates.contentHashCode()
            result = 31 * result + type.hashCode()
            return result
        }
    }

    @Serializable
    public data class CurrentUserRetweet(
        public val id: Long,
        @SerialName("id_str") public val idStr: String
    )

    @Serializable
    public data class ExtendedTweet(
        @SerialName("display_text_range") public val displayTextRange: IntArray,
        public val entities: StatusEntity,
        @SerialName("extended_entities") public val extendedEntities: StatusEntity? = null,
        @SerialName("full_text") public override val fullText: String? = null
    ): Status.ExtendedTweet {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as ExtendedTweet

            if (!displayTextRange.contentEquals(other.displayTextRange)) return false
            if (entities != other.entities) return false
            if (extendedEntities != other.extendedEntities) return false
            if (fullText != other.fullText) return false

            return true
        }

        override fun hashCode(): Int {
            var result = displayTextRange.contentHashCode()
            result = 31 * result + entities.hashCode()
            result = 31 * result + (extendedEntities?.hashCode() ?: 0)
            result = 31 * result + (fullText?.hashCode() ?: 0)
            return result
        }
    }

    @Serializable
    public data class MatchingRule(
        public val tag: String
    )
}
