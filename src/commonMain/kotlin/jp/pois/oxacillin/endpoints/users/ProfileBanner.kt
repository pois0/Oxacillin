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

@file:Suppress("UNUSED")

package jp.pois.oxacillin.endpoints.users

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Users
import jp.pois.oxacillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns a map of the available size variations of the specified user's profile banner. If the user has not uploaded a profile banner, a HTTP 404 will be served instead. This method can be used instead of string manipulation on the profile_banner_url returned in user objects as described in [Profile Images and Banners](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners).
 *
 * The profile banner data available at each size variant's URL is in PNG format.
 *
 * @param userId The ID of the user for whom to return results. Helpful for disambiguating when a valid user ID is also a valid screen name.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonGeneralApiAction] for [UserProfileBanner] model.
 */
public fun <T> Users.profileBannerByUserId(
    deserializer: DeserializationStrategy<T>,
    userId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = profileBanner(deserializer, userId, null, *options)

public inline fun <reified T> Users.profileBannerByUserId(
    userId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = profileBannerByUserId(deserializer(), userId, *options)

/**
 * Returns a map of the available size variations of the specified user's profile banner. If the user has not uploaded a profile banner, a HTTP 404 will be served instead. This method can be used instead of string manipulation on the profile_banner_url returned in user objects as described in [Profile Images and Banners](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners).
 *
 * The profile banner data available at each size variant's URL is in PNG format.
 *
 * @param screenName The screen name of the user for whom to return results. Helpful for disambiguating when a valid screen name is also a user ID.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonGeneralApiAction] for [UserProfileBanner] model.
 */
public fun <T> Users.profileBannerByScreenName(
    deserializer: DeserializationStrategy<T>,
    screenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = profileBanner(deserializer, null, screenName, *options)

public inline fun <reified T> Users.profileBannerByScreenName(
    screenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = profileBannerByScreenName(deserializer(), screenName, *options)

private fun <T> Users.profileBanner(
    deserializer: DeserializationStrategy<T>,
    userId: Long? = null,
    screenName: String? = null,
    vararg options: Option
) = client.session.get("/1.1/users/profile_banner.json") {
    parameters(
        "user_id" to userId,
        "screen_name" to screenName,
        *options
    )
}.json(deserializer)
