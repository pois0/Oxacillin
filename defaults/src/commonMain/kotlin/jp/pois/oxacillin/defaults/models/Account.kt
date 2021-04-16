package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

public class Account private constructor() {
    public data class Settings(
        @SerialName("address_book_live_sync_enabled") public val addressBookLiveSyncEnabled: Boolean,
        @SerialName("allow_ads_personalization") public val allowAdsPersonalization: Boolean,
        @SerialName("allow_authenticated_periscope_requests") public val allowAuthenticatedPeriscopeRequests: Boolean,
        @SerialName("allow_contributor_request") public val allowContributorRequest: String,
        @SerialName("allow_dm_groups_from") public val allowDmGroupsFrom: String,
        @SerialName("allow_dms_from") public val allowDmsFrom: String,
        @SerialName("allow_location_history_personalization") public val allowLocationHistoryPersonalization: Boolean,
        @SerialName("allow_logged_out_device_personalization") public val allowLoggedOutDevicePersonalization: Boolean,
        @SerialName("allow_media_tagging") public val allowMediaTagging: String,
        @SerialName("allow_sharing_data_for_third_party_personalization") public val allowSharingDataForThirdPartyPersonalization: Boolean,
        @SerialName("alt_text_compose_enabled") public val altTextComposeEnabled: String? = null,
        @SerialName("always_use_https") public val alwaysUseHttps: Boolean,
        @SerialName("country_code") public val countryCode: String,
        @SerialName("discoverable_by_email") public val discoverableByEmail: Boolean,
        @SerialName("discoverable_by_mobile_phone") public val discoverableByMobilePhone: Boolean,
        @SerialName("display_sensitive_media") public val displaySensitiveMedia: Boolean,
        @SerialName("dm_receipt_setting") public val dmReceiptSetting: String,
        @SerialName("geo_enabled") public val geoEnabled: Boolean,
        public val language: String,
        @SerialName("mention_filter") public val mentionFilter: String? = null,
        @SerialName("notifications_abuse_filter_quality") public val notificationsAbuseFilterQuality: String,
        @SerialName("notifications_filter_quality") public val notificationsFilterQuality: String,
        @SerialName("personalized_trends") public val personalizedTrends: Boolean,
        public val protected: Boolean,
        @SerialName("ranked_timeline_eligible") public val rankedTimelineEligible: String? = null,
        @SerialName("ranked_timeline_setting") public val rankedTimelineSetting: Int? = null,
        @SerialName("screen_name") public val screenName: String,
        @SerialName("settings_metadata") public val settingsMetadata: JsonElement,
        @SerialName("sleep_time") public val sleepTime: SleepTime,
        @SerialName("time_zone") public val timeZone: TimeZone,
        @SerialName("translator_type") public val translatorType: String,
        @SerialName("universal_quality_filtering_enabled") public val universalQualityFilteringEnabled: String,
        @SerialName("use_cookie_personalization") public val useCookiePersonalization: Boolean
    ) {
        @Serializable
        public data class SleepTime(
            public val enabled: Boolean,
            @SerialName("start_time") public val startTime: Long? = null,
            @SerialName("end_time") public val endTime: Long? = null
        )

        @Serializable
        public data class TimeZone(
            public val name: String,
            @SerialName("utf_offset") public val utfOffset: Int,
            @SerialName("tzinfo_name") public val tzinfoName: String
        )
    }

    @Serializable
    public data class VerifyCredentials(
        public val email: String,
        public val phone: Phone
    ) {
        @Serializable
        public data class Phone(
            public val address: String? = null,
            @SerialName("address_for_sms") public val addressForSms: String? = null,
            public val carrier: String? = null,
            @SerialName("country_code") public val countryCode: String? = null,
            @SerialName("country_name") public val countryName: String? = null,
            @SerialName("created_at") public val createdAt: String? = null,
            @SerialName("device_type") public val deviceType: String? = null,
            @SerialName("enabled_for") public val enabledFor: String? = null,
            public val id: Long? = null,
            public val verified: Boolean? = null
        )
    }
}