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
import jp.pois.oxacillin.endpoints.friendships.showByScreenName
import jp.pois.oxacillin.endpoints.friendships.showByUserId

/**
 * Returns detailed information about the relationship between two arbitrary users.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 * 
 * @param sourceId The user_id of the subject user.
 * @param targetId The user_id of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public inline fun Friendships.showByUserId(
    sourceId: Long,
    targetId: Long,
    vararg options: Option
): JsonGeneralApiAction<jp.pois.oxacillin.defaults.models.Friendships.Show> = client.friendships.showByUserId(sourceId, targetId, *options)

/**
 * Returns detailed information about the relationship between two arbitrary users.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 *
 * @param targetId The user_id of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public inline fun Friendships.showByUserId(
    targetId: Long,
    vararg options: Option
): JsonGeneralApiAction<jp.pois.oxacillin.defaults.models.Friendships.Show> = client.friendships.showByUserId(targetId, *options)

    /**
 * Returns detailed information about the relationship between two arbitrary users.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 *
 * @param sourceScreenName The screen_name of the subject user.
 * @param targetScreenName The screen_name of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public inline fun Friendships.showByScreenName(
    sourceScreenName: String,
    targetScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<jp.pois.oxacillin.defaults.models.Friendships.Show> = client.friendships.showByScreenName(sourceScreenName, targetScreenName, *options)

    /**
 * Returns detailed information about the relationship between two arbitrary users.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 *
 * @param targetScreenName The screen_name of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public inline fun Friendships.showByScreenName(
    targetScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<jp.pois.oxacillin.defaults.models.Friendships.Show> = client.friendships.showByScreenName(targetScreenName, *options)
