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

package jp.pois.oxacillin.endpoints

import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.core.session.ApiClientDsl

/**
 * Returns [FollowRequests] endpoint instance.
 * 
 * @return New [FollowRequests] endpoint instance.
 * @receiver Current [ApiClient] instance.
 */
@ApiClientDsl
public val ApiClient.followRequests: FollowRequests
    get() = FollowRequests(this)

/**
 * Collection of api endpoints related to follow requests.
 *
 * @constructor Creates new [FollowRequests] endpoint instance.
 * @param client Current [ApiClient] instance.
 * @see ApiClient.followRequests
 */
public inline class FollowRequests(override val client: ApiClient): Endpoint
