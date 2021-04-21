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

package jp.pois.oxacillin.defaults.endpoints.users

import jp.pois.oxacillin.core.request.action.JsonArrayApiAction
import jp.pois.oxacillin.defaults.endpoints.Users
import jp.pois.oxacillin.defaults.models.User
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.users
import jp.pois.oxacillin.endpoints.users.search

/**
 * Provides a simple, relevance-based search interface to public user accounts on Twitter. Try querying by topical interest, full name, company name, location, or other criteria. Exact match searches are not supported.
 * Only the first 1,000 matching results are available.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-search)
 * 
 * @param query The search query to run against people search.
 * @param page Specifies the page of results to retrieve.
 * @param count The number of potential user results to retrieve per page. This value has a maximum of 20.
 * @param includeEntities The entities node will not be included in embedded Tweet objects when set to false .
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonArrayApiAction] for [User] model.
 */
public inline fun Users.search(
    query: String,
    page: Int? = null,
    count: Int? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonArrayApiAction<User> = client.users.search(query, page, count, includeEntities, *options)
