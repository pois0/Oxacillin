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

package jp.pois.oxacillin.defaults.endpoints.collections.entries

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.CollectionEntries
import jp.pois.oxacillin.defaults.models.Collection
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.collectionEntries
import jp.pois.oxacillin.endpoints.collections.entries.add

/**
 * Add a specified Tweet to a Collection.
 * A collection will store up to a few thousand Tweets. Adding a Tweet to a collection beyond its allowed capacity will remove the oldest Tweet in the collection based on the time it was added to the collection.
 * Use [POST collections/entries/curate](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-entries-curate) to add Tweets to a Collection in bulk.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-entries-add)
 * 
 * @param id The identifier of the Collection receiving the Tweet.
 * @param tweetId The identifier of the Tweet to add to the Collection.
 * @param relativeTo The identifier of the Tweet used for relative positioning in a curation_reverse_chron ordered collection.
 * @param above Set to false to insert the specified tweet_id below the relative_to Tweet in the collection. Default: true
 * @param options Optional. Custom parameters of this request.
 * @receiver [CollectionEntries] endpoint instance.
 * @return [JsonGeneralApiAction] for [Collection.Entry.Result] model.
 */
public inline fun CollectionEntries.add(
    id: String,
    tweetId: Long,
    relativeTo: Long? = null,
    above: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<Collection.Entry.Result> = client.collectionEntries.add(id, tweetId, relativeTo, above, *options)
