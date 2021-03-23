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

package jp.pois.oxacillin.endpoints.mutes

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Mutes
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Un-mutes the user specified in the ID parameter for the authenticating user.
 * Returns the unmuted user when successful. Returns a string describing the failure condition when unsuccessful.
 * Actions taken in this method are asynchronous. Changes will be eventually consistent.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-mutes-users-destroy)
 *
 * @param screenName The screen name of the potentially muted user. Helpful for disambiguating when a valid screen name is also a user ID.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Mutes] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public fun <T> Mutes.destroyByScreenName(
    deserializer: DeserializationStrategy<T>,
    screenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = destroy(deserializer, screenName, null, *options)

public inline fun <reified T> Mutes.destroyByScreenName(
    screenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = destroyByScreenName(deserializer(), screenName, *options)

/**
 * Un-mutes the user specified in the ID parameter for the authenticating user.
 * Returns the unmuted user when successful. Returns a string describing the failure condition when unsuccessful.
 * Actions taken in this method are asynchronous. Changes will be eventually consistent.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/mute-block-report-users/api-reference/post-mutes-users-destroy)
 *
 * @param userId The ID of the potentially muted user. Helpful for disambiguating when a valid user ID is also a valid screen name.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Mutes] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public fun <T> Mutes.destroyByUserId(
    deserializer: DeserializationStrategy<T>,
    userId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = destroy(deserializer, null, userId, *options)

public inline fun <reified T> Mutes.destroyByUserId(
    userId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = destroyByUserId(deserializer(), userId, *options)

private fun <T> Mutes.destroy(
    deserializer: DeserializationStrategy<T>,
    screenName: String? = null,
    userId: Long? = null,
    vararg options: Option
) = client.session.post("/1.1/mutes/users/destroy.json") {
    formBody(
        "screen_name" to screenName,
        "user_id" to userId,
        *options
    )
}.json(deserializer)
