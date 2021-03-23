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

package jp.pois.oxacillin.endpoints.collections

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Collections
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Update information concerning a Collection owned by the currently authenticated user.
 * Partial updates are not currently supported: please provide name, description, and url whenever using this method. Omitted description or url parameters will be treated as if an empty string was passed, overwriting any previously stored value for the Collection.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/curate-a-collection/api-reference/post-collections-update)
 * 
 * @param id The identifier of the Collection to modify.
 * @param name The title of the Collection being created, in 25 characters or fewer.
 * @param description A brief description of this Collection in 160 characters or fewer.
 * @param url A fully-qualified URL to associate with this Collection.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Collections] endpoint instance.
 * @return [JsonGeneralApiAction] for [Collection.Model] model.
 */
public fun <T> Collections.update(
    deserializer: DeserializationStrategy<T>,
    id: String,
    name: String? = null,
    description: String? = null,
    url: String? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.post("/1.1/collections/update.json") {
    formBody(
        "id" to id,
        "name" to name,
        "description" to description,
        "url" to url,
        *options
    )
}.json(deserializer)

public inline fun <reified T> Collections.update(
    id: String,
    name: String? = null,
    description: String? = null,
    url: String? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = update(deserializer(), id, name, description, url, *options)
