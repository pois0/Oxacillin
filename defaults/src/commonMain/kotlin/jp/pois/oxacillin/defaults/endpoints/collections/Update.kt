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
import jp.pois.oxacillin.endpoints.collections.update

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
public inline fun Collections.update(
    id: String,
    name: String? = null,
    description: String? = null,
    url: String? = null,
    vararg options: Option
): JsonGeneralApiAction<Collection.Model> = client.collections.update(id, name, description, url, *options)
