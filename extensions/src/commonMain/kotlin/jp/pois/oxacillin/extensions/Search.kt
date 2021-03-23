package jp.pois.oxacillin.extensions

public interface Search {
    public val searchMetadata: jp.pois.oxacillin.extensions.Search.Metadata

    public interface Metadata {
        public val nextResults: String?
        public val refreshUrl: String?
    }
}