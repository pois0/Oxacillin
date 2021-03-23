package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.endpoints.lists.ListVisibilityMode
import jp.pois.oxacillin.models.CursorModel
import jp.pois.oxacillin.extensions.TwitterList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TwitterListImpl(
    val id: Long,
    override val name: String,
    override val mode: ListVisibilityMode,
    override val description: String
): TwitterList

@Serializable
internal class User(val id: Long)

@Serializable
internal class CursorUsers(
    @SerialName("next_cursor") override val nextCursor: Long,
    @SerialName("previous_cursor") override val previousCursor: Long,
    override val items: List<User>
): CursorModel<User>