package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.extensions.IndexedEntityModel

public interface UrlEntityModel: IndexedEntityModel {
    public val url: String
    public val expandedUrl: String
}
