package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.endpoints.lists.ListVisibilityMode
import jp.pois.oxacillin.extensions.TwitterList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TwitterList(
    @SerialName("created_at") public val createdAt: String,
    public override val description: String,
    public val following: Boolean,
    @SerialName("full_name") public val fullName: String,
    public val id: Long,
    @SerialName("id_str") public val idStr: String,
    @SerialName("member_count") public val memberCount: Int,
    public override val mode: ListVisibilityMode,
    public override val name: String,
    public val slug: String,
    @SerialName("subscriber_count") public val subscriberCount: Int,
    public val uri: String,
    public val user: User
): TwitterList