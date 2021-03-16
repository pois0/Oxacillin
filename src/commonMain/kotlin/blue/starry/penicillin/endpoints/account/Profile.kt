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

package blue.starry.penicillin.endpoints.account

import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.formBody
import blue.starry.penicillin.core.session.post
import blue.starry.penicillin.endpoints.Account
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.models.User

/**
 * Sets some values that users are able to set under the "Account" tab of their settings page. Only the parameters specified will be updated.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-update_profile)
 *
 * @param name Optional. Full name associated with the profile.
 * @param url URL associated with the profile. Will be prepended with `http://` if not present.
 * @param location The city or country describing where the user of the account is located. The contents are not normalized or geocoded in any way.
 * @param description A description of the user owning the account.
 * @param profileLinkColor Sets a hex value that controls the color scheme of links used on the authenticating user's profile page on twitter.com. This must be a valid hexadecimal value, and may be either three or six characters (ex: F00 or FF0000). This parameter replaces the deprecated (and separate) update_profile_colors API method.
 * @param includeEntities The entities node will not be included when set to *false*.
 * @param skipStatus When set to either true, t or 1 statuses will not be included in the returned user object.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Account] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public fun Account.updateProfile(
    name: String? = null,
    url: String? = null,
    location: String? = null,
    description: String? = null,
    profileLinkColor: String? = null,
    includeEntities: Boolean? = null,
    skipStatus: Boolean? = null,
    birthdateYear: Int? = null,
    birthdateMonth: Int? = null,
    birthdateDay: Int? = null,
    vararg options: Option
): JsonGeneralApiAction<User> = client.session.post("/1.1/account/update_profile.json") {
    formBody(
        "name" to name,
        "url" to url,
        "location" to location,
        "description" to description,
        "profile_link_color" to profileLinkColor,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus,
        "birthdate_year" to birthdateYear,
        "birthdate_month" to birthdateMonth,
        "birthdate_day" to birthdateDay,
        *options
    )

}.jsonObject { User(it, client) }

/**
 * Shorthand extension property to [Account.updateProfile].
 * @see Account.updateProfile
 */
public val Account.updateProfile: JsonGeneralApiAction<User>
    get() = updateProfile()
