package jp.pois.oxacillin.defaults.models.cursors

import jp.pois.oxacillin.defaults.models.TwitterList
import jp.pois.oxacillin.models.CursorModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CursorLists(
    @SerialName("next_cursor") public override val nextCursor: Long,
    @SerialName("next_cursor_str") public val nextCursorStr: String,
    @SerialName("previous_cursor") public override val previousCursor: Long,
    @SerialName("previous_cursor_str") public val previousCursorStr: String,
    @SerialName("total_count") public val totalCount: Int? = null,
    @SerialName("lists") public override val items: List<TwitterList>
): CursorModel<TwitterList>
