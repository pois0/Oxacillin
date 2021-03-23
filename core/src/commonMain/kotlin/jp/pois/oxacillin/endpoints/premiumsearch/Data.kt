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


package jp.pois.oxacillin.endpoints.premiumsearch

import jp.pois.oxacillin.core.request.action.PremiumSearchJsonApiAction
import jp.pois.oxacillin.core.request.buildJsonBody
import jp.pois.oxacillin.core.request.premiumSearchJsonObject
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.PremiumSearch
import jp.pois.oxacillin.endpoints.PremiumSearchEnvironment
import jp.pois.oxacillin.endpoints.environment
import jp.pois.oxacillin.endpoints.search.SearchProduct
import jp.pois.oxacillin.utils.toYYYYMMddHHmmss
import jp.pois.oxacillin.utils.deserializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.put


/**
 * Returns a collection of relevant [Tweets](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object) matching a specified query.
 * To learn how to use [Twitter Search](https://twitter.com/search) effectively, please see the [Premium search operators](https://developer.twitter.com/en/docs/tweets/search/guides/premium-operators) page for a list of available filter operators.
 * @param product Select either 30days or fullarchive.
 * @param label The label associated with your search developer environment.
 * @param query A UTF-8, URL-encoded search query of 500 characters maximum, including operators. Queries may additionally be limited by complexity.
 * @param tag Returned tweets each have a list of [MatchingRule] whose value of tag is given one.
 * @param fromDate Returns tweets created after the given datetime.
 * @param toDate Returns tweets created before the given datetime.
 * @param maxResults The number of tweets to return per page.
 * @param next Returns the next page of results.
 * @param options Optional. Custom parameters of this request.
 * @receiver [PremiumSearch] endpoint instance.
 * @return [PremiumSearchJsonApiAction] for [PremiumSearchData] model.
 */
public fun <T> PremiumSearch.data(
    deserializer: DeserializationStrategy<T>,
    product: SearchProduct,
    label: String,
    query: String,
    tag: String? = null,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    maxResults: Int? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = environment(product, label).data(deserializer, query, tag, fromDate, toDate, maxResults, next, *options)

public inline fun <reified T> PremiumSearch.data(
    product: SearchProduct,
    label: String,
    query: String,
    tag: String? = null,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    maxResults: Int? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = data(deserializer(), product, label, query, tag, fromDate, toDate, maxResults, next, *options)

/**
 * Returns a collection of relevant [Tweets](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object) matching a specified query.
 * To learn how to use [Twitter Search](https://twitter.com/search) effectively, please see the [Premium search operators](https://developer.twitter.com/en/docs/tweets/search/guides/premium-operators) page for a list of available filter operators.
 * @param query A UTF-8, URL-encoded search query of 500 characters maximum, including operators. Queries may additionally be limited by complexity.
 * @param tag Returned tweets each have a list of [MatchingRule] whose value of tag is given one.
 * @param fromDate Returns tweets created after the given datetime.
 * @param toDate Returns tweets created before the given datetime.
 * @param maxResults The number of tweets to return per page.
 * @param next Returns the next page of results.
 * @param options Optional. Custom parameters of this request.
 * @receiver [PremiumSearchEnvironment] endpoint instance.
 * @return [PremiumSearchJsonApiAction] for [PremiumSearchData] model.
 */
public fun <T> PremiumSearchEnvironment.data(
    deserializer: DeserializationStrategy<T>,
    query: String,
    tag: String? = null,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    maxResults: Int? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = client.session.post("$endpoint.json") {
    buildJsonBody(pairs = options) {
        put("query", query)
        put("tag", tag)
        put("fromDate", fromDate?.toYYYYMMddHHmmss())
        put("toDate", toDate?.toYYYYMMddHHmmss())
        put("maxResults", maxResults)
        put("next", next)
    }
}.premiumSearchJsonObject(this, deserializer)

public inline fun <reified T> PremiumSearchEnvironment.data(
    query: String,
    tag: String? = null,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    maxResults: Int? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = data(deserializer(), query, tag, fromDate, toDate, maxResults, next, *options)
