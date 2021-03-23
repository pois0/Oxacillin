package jp.pois.oxacillin.extensions

import jp.pois.oxacillin.endpoints.lists.ListVisibilityMode

public interface TwitterList {
    public val name: String
    public val mode: ListVisibilityMode
    public val description: String
}