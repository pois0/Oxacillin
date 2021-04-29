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


import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.Friendships
import jp.pois.oxacillin.defaults.models.Relationship
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.friendships
import jp.pois.oxacillin.endpoints.friendships.updateByScreenName
import jp.pois.oxacillin.endpoints.friendships.updateByUserId

/**
 * Enable or disable Retweets and device notifications from the specified user.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-update)
 * 
 * @param userId The ID of the user being followed.
 * @param device Enable/disable device notifications from the target user.
 * @param retweets Enable/disable Retweets from the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Relationship] model.
 */
public inline fun Friendships.updateByUserId(
    userId: Long,
    device: Boolean? = null,
    retweets: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<Relationship> = client.friendships.updateByUserId(userId, device, retweets, *options)

    /**
 * Enable or disable Retweets and device notifications from the specified user.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/post-friendships-update)
 *
 * @param screenName The screen name of the user being followed.
 * @param device Enable/disable device notifications from the target user.
 * @param retweets Enable/disable Retweets from the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Relationship] model.
 */
public inline fun Friendships.updateByScreenName(
    screenName: String,
    device: Boolean? = null,
    retweets: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<Relationship> = client.friendships.updateByScreenName(screenName, device, retweets, *options)
