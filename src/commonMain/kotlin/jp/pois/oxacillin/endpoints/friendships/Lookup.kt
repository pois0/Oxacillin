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

package jp.pois.oxacillin.endpoints.friendships

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Friendships
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided. Values for connections can be: following, following_requested, followed_by, none, blocking, muting.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup)
 * 
 * @param screenNames A list of screen names, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Lookup] model.
 */
public fun <T> Friendships.lookupByScreenNames(
    deserializer: DeserializationStrategy<T>,
    screenNames: List<String>,
    vararg options: Option
): JsonGeneralApiAction<T> = lookup(deserializer, screenNames, null, *options)

public inline fun <reified T> Friendships.lookupByScreenNames(
    screenNames: List<String>,
    vararg options: Option
): JsonGeneralApiAction<T> = lookupByScreenNames(deserializer(), screenNames, *options)

    /**
 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided. Values for connections can be: following, following_requested, followed_by, none, blocking, muting.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup)
 * 
 * @param userIds A list of user IDs, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Lookup] model.
 */
public fun <T> Friendships.lookupByUserIds(
    deserializer: DeserializationStrategy<T>,
    userIds: List<Long>,
    vararg options: Option
): JsonGeneralApiAction<T> = lookup(deserializer, null, userIds, *options)

public inline fun <reified T> Friendships.lookupByUserIds(
    userIds: List<Long>,
    vararg options: Option
): JsonGeneralApiAction<T> = lookupByUserIds(deserializer(), userIds, *options)

    private fun <T> Friendships.lookup(
    deserializer: DeserializationStrategy<T>,
    screenNames: List<String>? = null,
    userIds: List<Long>? = null,
    vararg options: Option
) = client.session.get("/1.1/friendships/lookup.json") {
    parameters(
        "screen_name" to screenNames?.joinToString(","),
        "user_id" to userIds?.joinToString(","),
        *options
    )
}.json(deserializer)
