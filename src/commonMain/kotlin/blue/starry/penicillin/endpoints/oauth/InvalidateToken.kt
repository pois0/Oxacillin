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

package blue.starry.penicillin.endpoints.oauth

import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.OAuth
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Allows a registered application to revoke an issued OAuth access_token by presenting its client credentials. Once an access_token has been invalidated, new creation attempts will yield a different Access Token and usage of the invalidated token will no longer be allowed.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/basics/authentication/api-reference/invalidate_access_token)
 * 
 * @param accessToken The access_token of user to be invalidated.
 * @param accessTokenSecret The access_token_secret of user to be invalidated.
 * @param options Optional. Custom parameters of this request.
 * @receiver [OAuth] endpoint instance.
 * @return [JsonGeneralApiAction] for [OAuthToken] model.
 */
public fun <T> OAuth.invalidateToken(
    deserializer: DeserializationStrategy<T>,
    accessToken: String,
    accessTokenSecret: String,
    vararg options: Option
): JsonGeneralApiAction<T> = invalidateTokenInternal(deserializer, accessToken, accessTokenSecret, *options)

public inline fun <reified T> OAuth.invalidateToken(
    accessToken: String,
    accessTokenSecret: String,
    vararg options: Option
): JsonGeneralApiAction<T> = invalidateToken(deserializer(), accessToken, accessTokenSecret, *options)

/**
 * Allows a registered application to revoke an issued OAuth access_token by presenting its client credentials. Once an access_token has been invalidated, new creation attempts will yield a different Access Token and usage of the invalidated token will no longer be allowed.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/basics/authentication/api-reference/invalidate_access_token)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [OAuth] endpoint instance.
 * @return [JsonGeneralApiAction] for [OAuthToken] model.
 */
public fun <T> OAuth.invalidateToken(
    deserializer: DeserializationStrategy<T>,
    vararg options: Option
): JsonGeneralApiAction<T> = invalidateTokenInternal(deserializer, client.session.credentials.accessToken!!, client.session.credentials.accessTokenSecret!!, *options)

public inline fun <reified T> OAuth.invalidateToken(
    vararg options: Option
): JsonGeneralApiAction<T> = invalidateToken(deserializer(), *options)

private fun <T> OAuth.invalidateTokenInternal(
    deserializer: DeserializationStrategy<T>,
    accessToken: String? = null,
    accessTokenSecret: String? = null,
    vararg options: Option
) = client.session.get("/oauth/invalidate_token") {
    parameters(
        "access_token" to accessToken,
        "access_token_secret" to accessTokenSecret,
        *options
    )
}.json(deserializer)
