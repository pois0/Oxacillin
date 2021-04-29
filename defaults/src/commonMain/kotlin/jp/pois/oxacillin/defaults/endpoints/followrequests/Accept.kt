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

package jp.pois.oxacillin.defaults.endpoints.followrequests

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.FollowRequests
import jp.pois.oxacillin.defaults.models.User
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.followRequests
import jp.pois.oxacillin.endpoints.followrequests.acceptByScreenName
import jp.pois.oxacillin.endpoints.followrequests.acceptByUserId

/**
 * Accepts the follow request from specific user.
 * 
 * @param screenName The screen name of the user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [FollowRequests] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public inline fun FollowRequests.acceptByScreenName(
    screenName: String,
    vararg  options: Option
): JsonGeneralApiAction<User> = client.followRequests.acceptByScreenName(screenName, *options)

/**
 * Accepts the follow request from specific user.
 *
 * @param userId The numeric ID of the user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [FollowRequests] endpoint instance.
 * @return [JsonGeneralApiAction] for [User] model.
 */
public inline fun FollowRequests.acceptByUserId(
    userId: Long,
    vararg  options: Option
): JsonGeneralApiAction<User> = client.followRequests.acceptByUserId(userId, *options)
