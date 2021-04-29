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

package jp.pois.oxacillin.endpoints.friendships


import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Friendships
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Allows the authenticating user to unfollow the user specified in the ID parameter.
 * Returns the unfollowed user when successful. Returns a string describing the failure condition when unsuccessful.
 * Actions taken in this method are asynchronous. Changes will be eventually consistent.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-destroy)
 * 
 * @param userId The ID of the user to unfollow.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 * @see destroyByScreenName
 */
public fun <T> Friendships.destroyByUserId(
    deserializer: DeserializationStrategy<T>,
    userId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = destroy(deserializer, userId, null, *options)

public inline fun <reified T> Friendships.destroyByUserId(
    userId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = destroyByUserId(deserializer(), userId, *options)

    /**
 * Allows the authenticating user to unfollow the user specified in the ID parameter.
 * Returns the unfollowed user when successful. Returns a string describing the failure condition when unsuccessful.
 * Actions taken in this method are asynchronous. Changes will be eventually consistent.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-destroy)
 *
 * @param screenName The screen name of the user to unfollow.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 * @see destroyByUserId
 */
public fun <T> Friendships.destroyByScreenName(
    deserializer: DeserializationStrategy<T>,
    screenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = destroy(deserializer, null, screenName, *options)

public inline fun <reified T> Friendships.destroyByScreenName(
    screenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = destroyByScreenName(deserializer(), screenName, *options)

    private fun <T> Friendships.destroy(
    deserializer: DeserializationStrategy<T>,
    userId: Long? = null,
    screenName: String? = null,
    vararg options: Option
) = client.session.post("/1.1/friendships/destroy.json") {
    formBody(
        "user_id" to userId,
        "screen_name" to screenName,
        *options
    )
}.json(deserializer)
