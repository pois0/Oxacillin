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

@file:Suppress("UNUSED")

package jp.pois.oxacillin.core.session

import io.ktor.http.HttpMethod
import jp.pois.oxacillin.core.request.ApiRequest
import jp.pois.oxacillin.core.request.ApiRequestBuilder
import jp.pois.oxacillin.core.request.EndpointHost

private fun Session.call(
    method: HttpMethod, path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}
): ApiRequest {
    return ApiRequestBuilder(client, method, host, path).apply(builder).build()
}

/**
 * Creates GET api request.
 */
public fun Session.get(path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}): ApiRequest {
    return call(HttpMethod.Get, path, host, builder)
}

/**
 * Creates POST api request.
 */
public fun Session.post(path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}): ApiRequest {
    return call(HttpMethod.Post, path, host, builder)
}

/**
 * Creates PUT api request.
 */
public fun Session.put(path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}): ApiRequest {
    return call(HttpMethod.Put, path, host, builder)
}

/**
 * Creates PATCH api request.
 */
public fun Session.patch(path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}): ApiRequest {
    return call(HttpMethod.Patch, path, host, builder)
}

/**
 * Creates DELETE api request.
 */
public fun Session.delete(path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}): ApiRequest {
    return call(HttpMethod.Delete, path, host, builder)
}

/**
 * Creates HEAD api request.
 */
public fun Session.head(path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}): ApiRequest {
    return call(HttpMethod.Head, path, host, builder)
}

/**
 * Creates OPTIONS api request.
 */
public fun Session.options(path: String, host: EndpointHost = EndpointHost.Default, builder: ApiRequestBuilder.() -> Unit = {}): ApiRequest {
    return call(HttpMethod.Options, path, host, builder)
}
