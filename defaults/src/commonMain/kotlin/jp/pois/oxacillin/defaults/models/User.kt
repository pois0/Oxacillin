/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
 *     Copyright (c) 2021 poispois
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jp.pois.oxacillin.defaults.models

import jp.pois.oxacillin.defaults.models.entities.UserEntity
import jp.pois.oxacillin.extensions.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
public data class User(
    public val id: Long,
    @SerialName("id_str") public val idStr: String,

    public val name: String,
    @SerialName("screen_name") public override val screenName: String,
    public val location: String,
    public val profileLocation: JsonElement? = null,
    public val description: String,
    public val url: String? = null,

    public val entities: UserEntity,
    public val protected: Boolean,
    @SerialName("followers_count") public val followersCount: Int,
    @SerialName("friends_count") public val friendsCount: Int,
    @SerialName("listed_count") public val listedCount: Int,
    @SerialName("created_at") public val createdAt: String,
    @SerialName("favourites_count") public val favouritesCount: Int,
    @SerialName("utc_offset") public val utcOffset: Int,
    @SerialName("time_zone") public val timeZone: String,
    @SerialName("geo_enabled") public val geoEnabled: Boolean,
    @SerialName("verified") public val verified: Boolean,
    @SerialName("statuses_count") public val statusesCount: Int,
    @SerialName("lang") public val langRaw: String? = null,
    @SerialName("contributors_enabled") public val contributorsEnabled: Boolean,
    @SerialName("is_translator") public val isTranslator: Boolean,
    @SerialName("is_translation_enabled") public val isTranslationEnabled: Boolean,

    @SerialName("profile_background_color") public val profileBackgroundColor: String,
    @SerialName("profile_background_image_url") public val profileBackgroundImageUrl: String? = null,
    @SerialName("profile_background_image_url_https") public val profileBackgroundImageUrlHttps: String? = null,
    @SerialName("profile_background_tile") public val profileBackgroundTile: Boolean,

    @SerialName("profile_image_url") public override val profileImageUrl: String,
    @SerialName("profile_image_url_https") public override val profileImageUrlHttps: String,

    @SerialName("profile_link_color") public val profileLinkColor: String,
    @SerialName("profile_sidebar_border_color") public val profileSidebarBorderColor: String,
    @SerialName("profile_sidebar_fill_color") public val profileSidebarFillColor: String,
    @SerialName("profile_text_color") public val profileTextColor: String,
    @SerialName("profile_background_image") public val profileBackgroundImage: Boolean,

    @SerialName("has_extended_profile") public val hasExtendedProfile: Boolean,
    @SerialName("default_profile") public val defaultProfile: Boolean,
    @SerialName("default_profile_image") public val defaultProfileImage: Boolean,
    @SerialName("following") public val following: Boolean,
    @SerialName("follow_request_sent") public val followRequestSent: Boolean,
    @SerialName("notifications") public val notifications: Boolean,

    @SerialName("muting") public val muting: Boolean,
    @SerialName("blocking") public val blocking: Boolean,
    @SerialName("blocked_by") public val blockedBy: Boolean,
    @SerialName("live_following") public val liveFollowing: Boolean,
    @SerialName("advertiser_account_service_levels") public val advertiserAccountServiceLevels: List<String>,
    @SerialName("advertiser_account_type") public val advertiserAccountType: String? = null,
    @SerialName("analytics_type") public val analyticsType: String? = null,
    @SerialName("business_profile_state") public val businessProfileState: String? = null,
    @SerialName("can_media_tag") public val canMediaTag: Boolean? = null,
    @SerialName("fast_followers_count") public val fastFollowersCount: Int? = null,
    @SerialName("followed_by") public val followedBy: Boolean? = null,
    @SerialName("has_custom_timelines") public val hasCustomTimelines: Boolean? = null,
    @SerialName("media_count") public val mediaCount: Int? = null,
    @SerialName("needs_phone_verification") public val needsPhoneVerification: Boolean? = null,
    @SerialName("normal_followers_count") public val normalFollowersCount: Int? = null,
    @SerialName("pinned_tweet_ids") public val pinnedTweetIds: List<Long>,
    @SerialName("pinned_tweet_ids_str") public val pinnedTweetIdsStr: List<String>,
    @SerialName("profile_banner_extensions") public val profileBannerExtensions: ProfileImageExtension? = null,
    @SerialName("profile_banner_url") public override val profileBannerUrl: String? = null,
    @SerialName("profile_image_extensions") public val profileImageExtensions: ProfileImageExtension? = null,
    @SerialName("profile_interstitial_type") public override val profileInterstitialType: String? = null,
    @SerialName("status") public val status: Status? = null,
    @SerialName("suspended") public val suspended: Boolean? = null,
    @SerialName("translator_type") public val translatorType: String,
    @SerialName("withheld_in_countries") public val withheldInCountries: List<String>,
    @SerialName("withheld_scope") public val withheldScope: String? = null
): User {
    @Serializable
    public data class ProfileImageExtension(
        public val mediaColor: MediaColor
    ) {
        @Serializable
        public data class MediaColor(
            public val r: JsonObject,
            public val ttl: Int
        )
    }
}
