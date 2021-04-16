package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.defaults.models.entities.StatusEntity
import jp.pois.oxacillin.extensions.CreatedAt
import jp.pois.oxacillin.extensions.MessageData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class DirectMessage(
    @SerialName("created_at") public val createdAt: CreatedAt,
    public override val entities: StatusEntity,
    public val id: Long,
    @SerialName("id_str") public val idStr: String,
    public val read: Boolean,
    public val recipient: User,
    @SerialName("recipient_id") public val recipientId: Long,
    @SerialName("recipient_id_str") public val recipientIdStr: String,
    @SerialName("recipient_screen_name") public val recipientScreenName: String,
    public val sender: User,
    @SerialName("sender_id") public val senderId: Long,
    @SerialName("sender_id_str") public val senderIdStr: String,
    @SerialName("sender_screen_name") public val senderScreenName: String,
    public override val text: String
): MessageData
