package jp.pois.oxacillin.extensions

import jp.pois.oxacillin.extensions.models.UrlEntityModel

public interface Status {
    public val cardUri: String?

    public val user: CommonUser

    public val entities: StatusEntity

    public val textRaw: String?
    public val fullTextRaw: String?
    public val retweetedStatus: Status?
    public val extendedTweet: ExtendedTweet?

    public interface ExtendedTweet {
        public val fullText: String?
    }

    public interface StatusEntity {
        public val media: List<UrlEntityModel>
        public val urls: List<UrlEntityModel>
    }
}