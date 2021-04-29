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

package jp.pois.oxacillin.defaults.endpoints.oauth


import jp.pois.oxacillin.defaults.endpoints.OAuth
import jp.pois.oxacillin.endpoints.Option
import io.ktor.http.*
import jp.pois.oxacillin.endpoints.oauth
import jp.pois.oxacillin.endpoints.oauth.authenticateUrl

/**
 * Allows a Consumer application to use an OAuth request_token to request user authorization.
 * This method is a replacement of [Section 6.2](http://oauth.net/core/1.0/#auth_step2) of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9) for applications using the callback authentication flow. The method will use the currently logged in user as the account for access authorization unless the force_login parameter is set to true.
 * This method differs from [GET oauth/authorize](https://developer.twitter.com/en/docs/basics/authentication/api-reference/authorize) in that if the user has already granted the application permission, the redirect will occur without the user having to re-approve the application. To realize this behavior, you must enable the Use Sign in with Twitter setting on your [application record](https://developer.twitter.com/apps).
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/basics/authentication/api-reference/authenticate)
 * 
 * @param forceLogin Forces the user to enter their credentials to ensure the correct users account is authorized.
 * @param screenName Prefills the username input box of the OAuth login screen with the given value.
 * @param options Optional. Custom parameters of this request.
 * @receiver [OAuth] endpoint instance.
 * @return [Url].
 */
public inline fun OAuth.authenticateUrl(
    requestToken: String,
    forceLogin: Boolean? = null,
    screenName: String? = null,
    vararg options: Option
): Url = client.oauth.authenticateUrl(requestToken, forceLogin, screenName, *options)
