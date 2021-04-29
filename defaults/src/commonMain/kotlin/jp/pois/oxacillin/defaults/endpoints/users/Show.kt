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

package jp.pois.oxacillin.defaults.endpoints.users

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.defaults.endpoints.Users
import jp.pois.oxacillin.defaults.models.User
import jp.pois.oxacillin.endpoints.users
import jp.pois.oxacillin.endpoints.users.showByScreenName
import jp.pois.oxacillin.endpoints.users.showByUserId

/**
 * Returns a [variety of information](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/user-object) about the user specified by the required user_id or screen_name parameter. The author's most recent Tweet will be returned inline when possible.
 * [GET users/lookup](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-lookup) is used to retrieve a bulk collection of user objects.
 * You must be following a protected user to be able to see their most recent Tweet. If you don't follow a protected user, the user's Tweet will be removed. A Tweet will not always be returned in the current_status field.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-show)
 * 
 * @param screenName The screen name of the user for whom to return results. Either a id or screen_name is required for this method.
 * @param includeEntities The entities node will not be included when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public inline fun Users.showByScreenName(
    screenName: String,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<User> = client.users.showByScreenName(screenName, includeEntities, *options)

/**
 * Returns a [variety of information](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/user-object) about the user specified by the required user_id or screen_name parameter. The author's most recent Tweet will be returned inline when possible.
 * [GET users/lookup](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-lookup) is used to retrieve a bulk collection of user objects.
 * You must be following a protected user to be able to see their most recent Tweet. If you don't follow a protected user, the user's Tweet will be removed. A Tweet will not always be returned in the current_status field.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-show)
 *
 * @param userId The ID of the user for whom to return results. Either an id or screen_name is required for this method.
 * @param includeEntities The entities node will not be included when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public inline fun Users.showByUserId(
    userId: Long,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<User> = client.users.showByUserId(userId, includeEntities, *options)
