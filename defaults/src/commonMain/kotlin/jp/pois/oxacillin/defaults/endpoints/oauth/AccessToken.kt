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
import jp.pois.oxacillin.endpoints.oauth
import jp.pois.oxacillin.endpoints.oauth.AccessTokenResponse
import jp.pois.oxacillin.endpoints.oauth.accessToken

/**
 * Allows a Consumer application to exchange the OAuth Request Token for an OAuth Access Token. This method fulfills [Section 6.3](http://oauth.net/core/1.0/#auth_step3) of the [OAuth 1.0 authentication](http://oauth.net/core/1.0/#anchor9) flow.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/basics/authentication/api-reference/access_token)
 *
 * @param verifier If using the OAuth web-flow, set this parameter to the value of the oauth_verifier returned in the callback URL. If you are using out-of-band OAuth, set this value to the pin-code. For OAuth 1.0a compliance this parameter is required. OAuth 1.0a is strictly enforced and applications not using the oauth_verifier will fail to complete the OAuth flow.
 * @param options Optional. Custom parameters of this request.
 * @receiver [OAuth] endpoint instance.
 * @return [AccessTokenResponse].
 */
public suspend inline fun OAuth.accessToken(
    consumerKey: String,
    consumerSecret: String,
    requestToken: String,
    requestTokenSecret: String,
    verifier: String,
    vararg options: Option
): AccessTokenResponse = client.oauth.accessToken(consumerKey, consumerSecret, requestToken, requestTokenSecret, verifier, *options)

/**
 * Allows a Consumer application to exchange the OAuth Request Token for an OAuth Access Token. This method fulfills [Section 6.3](http://oauth.net/core/1.0/#auth_step3) of the [OAuth 1.0 authentication](http://oauth.net/core/1.0/#anchor9) flow.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/basics/authentication/api-reference/access_token)
 *
 * @param verifier If using the OAuth web-flow, set this parameter to the value of the oauth_verifier returned in the callback URL. If you are using out-of-band OAuth, set this value to the pin-code. For OAuth 1.0a compliance this parameter is required. OAuth 1.0a is strictly enforced and applications not using the oauth_verifier will fail to complete the OAuth flow.
 * @param options Optional. Custom parameters of this request.
 * @receiver [OAuth] endpoint instance.
 * @return [AccessTokenResponse].
 */
public suspend inline fun OAuth.accessToken(
    requestToken: String,
    requestTokenSecret: String,
    verifier: String,
    vararg options: Option
): AccessTokenResponse = client.oauth.accessToken(client.session.credentials.consumerKey!!, client.session.credentials.consumerSecret!!, requestToken, requestTokenSecret, verifier, *options)
