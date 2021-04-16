package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.extensions.CreatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ActivityEvent(
    public val action: String,
    @SerialName("max_position") public val maxPosition: String,
    @SerialName("min_position") public val minPosition: String,
    @SerialName("created_at") public val createdAt: CreatedAt,
    @SerialName("target_objects") public val targetObjects: List<Status>,
    @SerialName("target_objects_size") public val targetObjectsSize: Int,
    public val targets: List<User>,
    @SerialName("targets_size") public val targetsSize: Int,
    public val sources: List<User>,
    @SerialName("sources_size") public val sourcesSize: Int
)