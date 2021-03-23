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

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Account
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns an HTTP 200 OK response code and a representation of the requesting user if authentication was successful; returns a 401 status code and an error message if not. Use this method to test if supplied user credentials are valid.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/get-account-verify_credentials)
 * 
 * @param includeEntities Optional. The entities node will not be included when set to false.
 * @param skipStatus Optional. When set to either true, t or 1 statuses will not be included in the returned user object.
 * @param includeEmail Optional. When set to true email will be returned in the user objects as a string. If the user does not have an email address on their account, or if the email address is not verified, null will be returned.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Account] endpoint instance.
 * @return [JsonGeneralApiAction] for [VerifyCredentials] model.
 */
public fun <T> Account.verifyCredentials(
    deserializer: DeserializationStrategy<T>,
    includeEntities: Boolean? = null,
    skipStatus: Boolean? = null,
    includeEmail: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.get("/1.1/account/verify_credentials.json") {
    parameters(
        "include_entities" to includeEntities,
        "skip_status" to skipStatus,
        "include_email" to includeEmail,
        *options
    )
}.json(deserializer)

public inline fun <reified T> Account.verifyCredentials(
    includeEntities: Boolean? = null,
    skipStatus: Boolean? = null,
    includeEmail: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = verifyCredentials(deserializer(), includeEntities, skipStatus, includeEmail, *options)
