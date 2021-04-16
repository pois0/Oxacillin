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

package jp.pois.oxacillin.defaults.endpoints.collections

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.Collections
import jp.pois.oxacillin.defaults.models.Collection
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.collections
import jp.pois.oxacillin.endpoints.collections.CollectionTimelineOrder
import jp.pois.oxacillin.endpoints.collections.create

/**
 * Create a Collection owned by the currently authenticated user.
 * The API endpoint may refuse to complete the request if the authenticated user has exceeded the total number of allowed collections for their account.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-create)
 * 
 * @param name The title of the collection being created, in 25 characters or less.
 * @param description A brief description of this collection in 160 characters or fewer.
 * @param url A fully-qualified URL to associate with this collection.
 * @param timelineOrder Order Tweets chronologically or in the order they are added to a Collection.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Collections] endpoint instance.
 * @return [JsonGeneralApiAction] for [Collection.Model] model.
 */
public inline fun Collections.create(
    name: String,
    description: String? = null,
    url: String? = null,
    timelineOrder: CollectionTimelineOrder = CollectionTimelineOrder.Default,
    vararg options: Option
): JsonGeneralApiAction<Collection.Model> = client.collections.create(name, description, url, timelineOrder, *options)
