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

package jp.pois.oxacillin.defaults.endpoints

import jp.pois.oxacillin.core.session.ApiClient

/**
 * Returns [Trends] endpoint instance.

 * [Twitter API reference](https://developer.twitter.com/en/docs/trends/trends-for-location)
 *
 * @return New [Trends] endpoint instance.
 * @receiver Current [ApiClient] instance.
 */
@DefaultEndpointDsl
public val DefaultEndpoint.trends: Trends
    get() = Trends(this.client)

/**
 * Collection of api endpoints related to trends.
 *
 * @constructor Creates new [Trends] endpoint instance.
 * @param client Current [ApiClient] instance.
 * @see ApiClient.trends
 */
public inline class Trends(override val client: ApiClient): Endpoint
