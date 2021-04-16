package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserProfileBanner(
    @SerialName("1080x360") public val resolution1080x360: Banner? = null,
    @SerialName("1500x500") public val resolution1500x500: Banner? = null,
    @SerialName("300x100") public val resolution300x100: Banner? = null,
    @SerialName("600x200") public val resolution600x200: Banner? = null,
    public val ipad: Banner? = null,
    @SerialName("ipad_retina") public val ipadRetina: Banner? = null,
    public val mobile: Banner? = null,
    @SerialName("mobile_retina") public val mobileRetina: Banner? = null,
    public val web: Banner? = null,
    @SerialName("web_retina") public val webRetina: Banner? = null
) {
    @Serializable
    public data class Banner(
        public val h: Int,
        public val w: Int,
        public val url: String
    )
}
