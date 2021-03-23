package jp.pois.oxacillin.extensions

import jp.pois.oxacillin.extensions.models.UrlEntityModel

public interface MessageData {
    public val text: String
    public val entities: Entities

    public interface Entities {
        public val urls: List<UrlEntityModel>
    }
}