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

package jp.pois.oxacillin.defaults.endpoints.favorites

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.Favorites
import jp.pois.oxacillin.defaults.models.Status
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.favorites
import jp.pois.oxacillin.endpoints.favorites.destroy

/**
 * Note: favorites are now known as likes.
 * Unfavorites (un-likes) the Tweet specified in the ID parameter as the authenticating user. Returns the un-liked Tweet when successful.
 * The process invoked by this method is asynchronous. The immediately returned Tweet object may not indicate the resultant favorited status of the Tweet. A 200 OK response from this method will indicate whether the intended action was successful or not.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-favorites-destroy)
 *
 * @param id The numerical ID of the Tweet to like.
 * @param includeEntities The entities node will be omitted when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Favorites] endpoint instance.
 * @return [JsonGeneralApiAction] for [Status] model.
 */
public inline fun Favorites.destroy(
    id: Long,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<Status> = client.favorites.destroy(id, includeEntities, *options)
