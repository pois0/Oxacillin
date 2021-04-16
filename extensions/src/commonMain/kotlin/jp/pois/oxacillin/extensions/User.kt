package jp.pois.oxacillin.extensions

public interface User {
    public val screenName: String

    public val profileInterstitialType: String?
    public val profileImageUrl: String
    public val profileImageUrlHttps: String
    public val profileBannerUrl: String?
}