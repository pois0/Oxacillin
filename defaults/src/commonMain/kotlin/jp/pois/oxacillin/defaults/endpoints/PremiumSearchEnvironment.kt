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

package jp.pois.oxacillin.defaults.endpoints

import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.endpoints.PremiumSearchEnvironment
import jp.pois.oxacillin.endpoints.environment
import jp.pois.oxacillin.endpoints.premiumSearch
import jp.pois.oxacillin.endpoints.search.SearchProduct

/**
 * Returns [PremiumSearchEnvironment] endpoint instance.
 * @param product Select either 30days or fullarchive.
 * @param label The label associated with your search developer environment.
 * @return New [PremiumSearchEnvironment] endpoint instance.
 * @receiver Current [ApiClient] instance.
 */
@DefaultEndpointDsl
public fun PremiumSearch.environment(
    product: SearchProduct,
    label: String
): PremiumSearchEnvironment = client.premiumSearch.environment(product, label)

public val PremiumSearchEnvironment.default: jp.pois.oxacillin.defaults.endpoints.PremiumSearchEnvironment
    get() = PremiumSearchEnvironment(this)

public inline class PremiumSearchEnvironment(public val env: PremiumSearchEnvironment)