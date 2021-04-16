package jp.pois.oxacillin.extensions

import jp.pois.oxacillin.extensions.models.UrlEntityModel

public interface Status {
    public val cardUri: String?

    public val user: jp.pois.oxacillin.extensions.User

    public val entities: jp.pois.oxacillin.extensions.Status.StatusEntity

    public val textRaw: String?
    public val fullTextRaw: String?
    public val retweetedStatus: jp.pois.oxacillin.extensions.Status?
    public val extendedTweet: jp.pois.oxacillin.extensions.Status.ExtendedTweet?

    public interface ExtendedTweet {
        public val fullText: String?
    }

    public interface StatusEntity {
        public val media: List<UrlEntityModel>
        public val urls: List<UrlEntityModel>
    }
}