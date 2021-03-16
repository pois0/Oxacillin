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

package blue.starry.penicillin.endpoints.friendships

import blue.starry.penicillin.core.request.action.JsonObjectApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.Friendships
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.models.Friendships.Lookup

/**
 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided. Values for connections can be: following, following_requested, followed_by, none, blocking, muting.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup)
 * 
 * @param screenNames A list of screen names, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonObjectApiAction] for [Lookup] model.
 */
public fun Friendships.lookupByScreenNames(
    screenNames: List<String>,
    vararg options: Option
): JsonArrayApiAction<Lookup> = lookup(screenNames, null, *options)

/**
 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided. Values for connections can be: following, following_requested, followed_by, none, blocking, muting.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup)
 * 
 * @param userIds A list of user IDs, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonObjectApiAction] for [Lookup] model.
 */
public fun Friendships.lookupByUserIds(
    userIds: List<Long>,
    vararg options: Option
): JsonArrayApiAction<Lookup> = lookup(null, userIds, *options)

private fun Friendships.lookup(
    screenNames: List<String>? = null,
    userIds: List<Long>? = null,
    vararg options: Option
) = client.session.get("/1.1/friendships/lookup.json") {
    parameters(
        "screen_name" to screenNames?.joinToString(","),
        "user_id" to userIds?.joinToString(","),
        *options
    )
}.jsonArray { Lookup(it, client) }
