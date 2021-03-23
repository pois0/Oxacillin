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

package jp.pois.oxacillin.endpoints.collections.entries

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.CollectionEntries
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Remove the specified Tweet from a Collection.
 * Use [POST collections/entries/curate](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-entries-curate) to remove Tweets from a Collection in bulk.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-entries-remove)
 * 
 * @param id The identifier of the target Collection.
 * @param tweetId The identifier of the Tweet to remove.
 * @param options Optional. Custom parameters of this request.
 * @receiver [CollectionEntries] endpoint instance.
 * @return [JsonGeneralApiAction] for [Collection.Entry.Result] model.
 */
public fun <T> CollectionEntries.remove(
    deserializer: DeserializationStrategy<T>,
    id: String,
    tweetId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.post("/1.1/collections/entries/remove.json") {
    formBody(
        "id" to id,
        "tweet_id" to tweetId,
        *options
    )
}.json(deserializer)

public inline fun <reified T> CollectionEntries.remove(
    id: String,
    tweetId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = remove(deserializer(), id, tweetId, *options)
