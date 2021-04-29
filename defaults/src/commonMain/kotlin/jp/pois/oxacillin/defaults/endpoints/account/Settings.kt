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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.pois.oxacillin.defaults.endpoints.account

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.Account
import jp.pois.oxacillin.defaults.models.Account.Settings
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.account
import jp.pois.oxacillin.endpoints.account.settings
import jp.pois.oxacillin.endpoints.account.updateSettings

/**
 * Returns settings (including current trend, geo and sleep time information) for the authenticating user.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/get-account-settings)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [Account] endpoint instance.
 * @return [JsonGeneralApiAction] for [Settings] model.
 */
public inline fun Account.settings(
    vararg options: Option
): JsonGeneralApiAction<Settings> = client.account.settings(*options)

/**
 * Shorthand extension property to [Account.settings].
 * @see Account.settings
 */
public inline val Account.settings: JsonGeneralApiAction<Settings>
    get() = settings()

    /**
 * Updates the authenticating user's settings.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-settings)
 *
 * @param sleepTimeEnabled Optional. When set to true, t or 1, will enable sleep time for the user. Sleep time is the time when push or SMS notifications should not be sent to the user.
 * @param startSleepTime Optional. The hour that sleep time should begin if it is enabled. The value for this parameter should be provided in [ISO 8601](http://en.wikipedia.org/wiki/ISO_8601) format (i.e. 00-23). The time is considered to be in the same timezone as the user's time_zone setting.
 * @param endSleepTime Optional. The hour that sleep time should end if it is enabled. The value for this parameter should be provided in [ISO 8601](http://en.wikipedia.org/wiki/ISO_8601) format (i.e. 00-23). The time is considered to be in the same timezone as the user's time_zone setting.
 * @param timeZone Optional. The timezone dates and times should be displayed in for the user. The timezone must be one of the [Rails TimeZone](http://api.rubyonrails.org/classes/ActiveSupport/TimeZone.html) names.
 * @param trendLocationWoeid Optional. The Yahoo! Where On Earth ID to use as the user's default trend location. Global information is available by using 1 as the WOEID. The WOEID must be one of the locations returned by [GET trends/available](https://developer.twitter.com/en/docs/trends/locations-with-trending-topics/api-reference/get-trends-available).
 * @param lang Optional. The language which Twitter should render in for this user. The language must be specified by the appropriate two letter ISO 639-1 representation. Currently supported languages are provided by [this endpoint](https://developer.twitter.com/en/docs/developer-utilities/supported-languages/api-reference/get-help-languages).
 * @param options Optional. Custom parameters of this request.
 * @receiver [Account] endpoint instance.
 * @return [JsonGeneralApiAction] for [Settings] model.
 */
public inline fun Account.updateSettings(
    sleepTimeEnabled: Boolean? = null,
    startSleepTime: Int? = null,
    endSleepTime: Int? = null,
    timeZone: String? = null,
    trendLocationWoeid: Int? = null,
    lang: String? = null,
    vararg options: Option
): JsonGeneralApiAction<Settings> = client.account.updateSettings(sleepTimeEnabled, startSleepTime, endSleepTime, timeZone, trendLocationWoeid, lang, *options)

/**
 * Shorthand extension property to [Account.updateSettings].
 * @see Account.updateSettings
 */
public inline val Account.updateSettings: JsonGeneralApiAction<Settings>
    get() = updateSettings()
