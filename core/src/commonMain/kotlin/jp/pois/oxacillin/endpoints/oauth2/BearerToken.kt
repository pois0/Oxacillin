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

@file:Suppress("UNUSED")

package jp.pois.oxacillin.endpoints.oauth2

import jp.pois.oxacillin.core.auth.AuthorizationType
import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.OAuth2
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Allows a registered application to obtain an OAuth 2 Bearer Token, which can be used to make API requests on an application's own behalf, without a user context. This is called [Application-only authentication](https://developer.twitter.com/en/docs/basics/authentication/overview/application-only).
 * A Bearer Token may be invalidated using oauth2/invalidate_token. Once a Bearer Token has been invalidated, new creation attempts will yield a different Bearer Token and usage of the previous token will no longer be allowed.
 * Only one bearer token may exist outstanding for an application, and repeated requests to this method will yield the same already-existent token until it has been invalidated.
 * Successful responses include a JSON-structure describing the awarded Bearer Token.
 * Tokens received by this method should be cached. If attempted too frequently, requests will be rejected with a HTTP 403 with code 99.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/basics/authentication/api-reference/token)
 * 
 * @param grantType Specifies the type of grant being requested by the application. At this time, only client_credentials is allowed. See [Application-Only Authentication](https://developer.twitter.com/oauth/application-only) for more information.
 * @param options Optional. Custom parameters of this request.
 * @receiver [OAuth2] endpoint instance.
 * @return [JsonGeneralApiAction] for [OAuthToken] model.
 */
public fun <T> OAuth2.bearerToken(
    deserializer: DeserializationStrategy<T>,
    grantType: String = "client_credentials",
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.post("/oauth2/token") {
    authorizationType = AuthorizationType.OAuth2RequestToken

    formBody(
        "grant_type" to grantType,
        *options
    )
}.json(deserializer)

public inline fun <reified T> OAuth2.bearerToken(
    grantType: String = "client_credentials",
    vararg options: Option
): JsonGeneralApiAction<T> = bearerToken(deserializer(), grantType, *options)
