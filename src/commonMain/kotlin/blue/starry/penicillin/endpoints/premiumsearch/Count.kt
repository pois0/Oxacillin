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

package blue.starry.penicillin.endpoints.premiumsearch

import blue.starry.penicillin.core.request.action.PremiumSearchJsonApiAction
import blue.starry.penicillin.core.request.jsonBody
import blue.starry.penicillin.core.session.post
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.endpoints.PremiumSearch
import blue.starry.penicillin.endpoints.PremiumSearchEnvironment
import blue.starry.penicillin.endpoints.environment
import blue.starry.penicillin.endpoints.search.SearchBucket
import blue.starry.penicillin.endpoints.search.SearchProduct
import blue.starry.penicillin.extensions.toYYYYMMddHHmmss
import blue.starry.penicillin.util.deserializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns counts data [Tweets](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object) for the specified query.
 * To learn how to use [Twitter Search](https://twitter.com/search) effectively, please see the [Premium search operators](https://developer.twitter.com/en/docs/tweets/search/guides/premium-operators) page for a list of available filter operators.
 * @param product Select either 30days or fullarchive.
 * @param label The label associated with your search developer environment.
 * @param query A UTF-8, URL-encoded search query of 500 characters maximum, including operators. Queries may additionally be limited by complexity.
 * @param fromDate Returns tweets created after the given datetime.
 * @param toDate Returns tweets created before the given datetime.
 * @param bucket Returns count data for every day. hour or minute in the set timeframe.
 * @param next Returns the next page of results.
 * @param options Optional. Custom parameters of this request.
 * @receiver [PremiumSearch] endpoint instance.
 * @return [PremiumSearchJsonApiAction] for [PremiumSearchCount] model.
 */
public fun <T> PremiumSearch.count(
    deserializer: DeserializationStrategy<T>,
    product: SearchProduct,
    label: String,
    query: String,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    bucket: SearchBucket? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = environment(product, label).count(deserializer, query, fromDate, toDate, bucket, next, *options)

public inline fun <reified T> PremiumSearch.count(
    product: SearchProduct,
    label: String,
    query: String,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    bucket: SearchBucket? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = count(deserializer(), product, label, query, fromDate, toDate, bucket, next, *options)

    /**
 * Returns counts data [Tweets](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object) for the specified query.
 * To learn how to use [Twitter Search](https://twitter.com/search) effectively, please see the [Premium search operators](https://developer.twitter.com/en/docs/tweets/search/guides/premium-operators) page for a list of available filter operators.
 * @param query A UTF-8, URL-encoded search query of 500 characters maximum, including operators. Queries may additionally be limited by complexity.
 * @param fromDate Returns tweets created after the given datetime.
 * @param toDate Returns tweets created before the given datetime.
 * @param bucket Returns count data for every day. hour or minute in the set timeframe.
 * @param next Returns the next page of results.
 * @param options Optional. Custom parameters of this request.
 * @receiver [PremiumSearchEnvironment] endpoint instance.
 * @return [PremiumSearchJsonApiAction] for [PremiumSearchCount] model.
 */
public fun <T> PremiumSearchEnvironment.count(
    deserializer: DeserializationStrategy<T>,
    query: String,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    bucket: SearchBucket? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = client.session.post("$endpoint/counts.json") {
    jsonBody(
        "query" to query,
        "fromDate" to fromDate?.toYYYYMMddHHmmss(),
        "toDate" to toDate?.toYYYYMMddHHmmss(),
        "bucket" to bucket?.value,
        "next" to next,
        *options
    )
}.premiumSearchJsonObject(this, deserializer)

public inline fun <reified T> PremiumSearchEnvironment.count(
    query: String,
    fromDate: LocalDateTime? = null,
    toDate: LocalDateTime? = null,
    bucket: SearchBucket? = null,
    next: String? = null,
    vararg options: Option
): PremiumSearchJsonApiAction<T> = count(deserializer(), query, fromDate, toDate, bucket, next, *options)
