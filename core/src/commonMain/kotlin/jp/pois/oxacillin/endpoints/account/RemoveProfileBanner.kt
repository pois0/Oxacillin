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

package jp.pois.oxacillin.endpoints.account

import jp.pois.oxacillin.core.request.action.EmptyApiAction
import jp.pois.oxacillin.core.request.empty
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Account
import jp.pois.oxacillin.endpoints.Option

/**
 * Removes the uploaded profile banner for the authenticating user. Returns HTTP 200 upon success.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-remove_profile_banner)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [Account] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Account.removeProfileBanner(
    vararg options: Option
): EmptyApiAction = client.session.post("/1.1/account/remove_profile_banner.json") {
    formBody(*options)
}.empty()

/**
 * Shorthand property to [Account.removeProfileBanner].
 * @see Account.removeProfileBanner
 */
public val Account.removeProfileBanner: EmptyApiAction
    get() = removeProfileBanner()
