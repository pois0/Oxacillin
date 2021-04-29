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

package jp.pois.oxacillin.defaults.endpoints.friendships

import jp.pois.oxacillin.core.request.action.JsonArrayApiAction
import jp.pois.oxacillin.defaults.endpoints.Friendships
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.friendships
import jp.pois.oxacillin.endpoints.friendships.lookupByScreenNames
import jp.pois.oxacillin.endpoints.friendships.lookupByUserIds

/**
 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided. Values for connections can be: following, following_requested, followed_by, none, blocking, muting.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup)
 * 
 * @param screenNames A list of screen names, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonArrayApiAction] for [jp.pois.oxacillin.defaults.models.Friendships.Lookup] model.
 */
public inline fun Friendships.lookupByScreenNames(
    screenNames: List<String>,
    vararg options: Option
): JsonArrayApiAction<jp.pois.oxacillin.defaults.models.Friendships.Lookup> = client.friendships.lookupByScreenNames(screenNames, *options)

    /**
 * Returns the relationships of the authenticating user to the comma-separated list of up to 100 screen_names or user_ids provided. Values for connections can be: following, following_requested, followed_by, none, blocking, muting.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-lookup)
 * 
 * @param userIds A list of user IDs, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonArrayApiAction] for [jp.pois.oxacillin.defaults.models.Friendships.Lookup] model.
 */
public inline fun Friendships.lookupByUserIds(
    userIds: List<Long>,
    vararg options: Option
): JsonArrayApiAction<jp.pois.oxacillin.defaults.models.Friendships.Lookup> = client.friendships.lookupByUserIds(userIds, *options)
