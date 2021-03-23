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

package jp.pois.oxacillin.endpoints.friendships

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Friendships
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns detailed information about the relationship between two arbitrary users.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 * 
 * @param sourceId The user_id of the subject user.
 * @param targetId The user_id of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public fun <T> Friendships.showByUserId(
    deserializer: DeserializationStrategy<T>,
    sourceId: Long,
    targetId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = show(deserializer, sourceId, null, targetId, null, *options)

public inline fun <reified T> Friendships.showByUserId(
    sourceId: Long,
    targetId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = showByUserId(deserializer(), sourceId, targetId, *options)

/**
 * Returns detailed information about the relationship between two arbitrary users.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 *
 * @param targetId The user_id of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public fun <T> Friendships.showByUserId(
    deserializer: DeserializationStrategy<T>,
    targetId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = show(deserializer, null, null, targetId, null, *options)

public inline fun <reified T> Friendships.showByUserId(
    targetId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = showByUserId(deserializer(), targetId, *options)

    /**
 * Returns detailed information about the relationship between two arbitrary users.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 *
 * @param sourceScreenName The screen_name of the subject user.
 * @param targetScreenName The screen_name of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public fun <T> Friendships.showByScreenName(
    deserializer: DeserializationStrategy<T>,
    sourceScreenName: String,
    targetScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = show(deserializer, null, sourceScreenName, null, targetScreenName, *options)

public inline fun <reified T> Friendships.showByScreenName(
    sourceScreenName: String,
    targetScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = showByScreenName(deserializer(), sourceScreenName, targetScreenName, *options)

    /**
 * Returns detailed information about the relationship between two arbitrary users.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friendships-show)
 *
 * @param targetScreenName The screen_name of the target user.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friendships] endpoint instance.
 * @return [JsonGeneralApiAction] for [Show] model.
 */
public fun <T> Friendships.showByScreenName(
    deserializer: DeserializationStrategy<T>,
    targetScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = show(deserializer, null, null, null, targetScreenName, *options)

public inline fun <reified T> Friendships.showByScreenName(
    targetScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = showByScreenName(deserializer(), targetScreenName, *options)

private fun <T> Friendships.show(
    deserializer: DeserializationStrategy<T>,
    sourceId: Long? = null,
    sourceScreenName: String? = null,
    targetId: Long? = null,
    targetScreenName: String? = null,
    vararg options: Option
) = client.session.get("/1.1/friendships/show.json") {
    parameters(
        "source_id" to sourceId,
        "source_screen_name" to sourceScreenName,
        "target_id" to targetId,
        "target_screen_name" to targetScreenName,
        *options
    )
}.json(deserializer)
