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

package blue.starry.penicillin.endpoints.collections.entries

import blue.starry.jsonkt.JsonObject
import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.jsonBody
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.post
import blue.starry.penicillin.endpoints.CollectionEntries
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.models.Collection

/**
 * Curate a Collection by adding or removing Tweets in bulk. Updates must be limited to 100 cumulative additions or removals per request.
 * Use [POST collections/entries/add](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-entries-add) and [POST collections/entries/remove](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-entries-remove) to add or remove a single Tweet.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-entries-curate)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [CollectionEntries] endpoint instance.
 * @return [JsonGeneralApiAction] for [Collection.Entry.Result] model.
 */
public fun CollectionEntries.curate(
    payload: JsonObject,
    vararg options: Option
): JsonGeneralApiAction<Collection.Entry.Result> = client.session.post("/1.1/collections/entries/curate.json") {
    parameters(*options)
    jsonBody(payload)
}.jsonObject { Collection.Entry.Result(it, client) }
