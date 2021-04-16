/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
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

package jp.pois.oxacillin.defaults.endpoints.users

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.defaults.endpoints.Users
import jp.pois.oxacillin.defaults.models.User
import jp.pois.oxacillin.endpoints.users
import jp.pois.oxacillin.endpoints.users.reportSpamByScreenName
import jp.pois.oxacillin.endpoints.users.reportSpamByUserId

/**
 * Report the specified user as a spam account to Twitter. Additionally, optionally performs the equivalent of [POST blocks/create](https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-blocks-create) on behalf of the authenticated user.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-users-report_spam)
 * 
 * @param screenName The screen_name of the user to report as a spammer. Helpful for disambiguating when a valid screen name is also a user ID.
 * @param performBlock Whether the account should be blocked by the authenticated user, as well as being reported for spam.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public inline fun Users.reportSpamByScreenName(
    screenName: String,
    performBlock: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<User> = client.users.reportSpamByScreenName(screenName, performBlock, *options)

/**
 * Report the specified user as a spam account to Twitter. Additionally, optionally performs the equivalent of [POST blocks/create](https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-blocks-create) on behalf of the authenticated user.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-users-report_spam)
 *
 * @param userId The ID of the user to report as a spammer. Helpful for disambiguating when a valid user ID is also a valid screen name.
 * @param performBlock Whether the account should be blocked by the authenticated user, as well as being reported for spam.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public inline fun Users.reportSpamByUserId(
    userId: Long,
    performBlock: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<User> = client.users.reportSpamByUserId(userId, performBlock, *options)
