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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.pois.oxacillin.defaults.endpoints.friendships


import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.Friendships
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.friendships
import jp.pois.oxacillin.endpoints.friendships.noRetweetsIds

/**
 * Returns a collection of user_ids that the currently authenticated user does not want to receive retweets from.
 * Use [POST friendships/update](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-update) to set the "no retweets" status for a given user account on behalf of the current user.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-no_retweets-ids)
 * 
 * @param stringifyIds Some programming environments will not consume Twitter IDs due to their size. Provide this option to have IDs returned as strings instead. Read more about [Twitter IDs](https://developer.twitter.com/en/docs/basics/twitter-ids). This parameter is important to use in Javascript environments.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [DelegatedAction] for [List] of [Long].
 */
public inline fun Friendships.noRetweetsIds(
    stringifyIds: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<LongArray> = client.friendships.noRetweetsIds(stringifyIds, *options)
