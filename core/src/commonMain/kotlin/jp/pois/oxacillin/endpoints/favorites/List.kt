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

package jp.pois.oxacillin.endpoints.favorites

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Favorites
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Note: favorites are now known as likes.
 * Returns the 20 most recent Tweets liked by the authenticating or specified user.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-favorites-list)
 *
 * @param count Specifies the number of records to retrieve. Must be less than or equal to 200; defaults to 20. The value of count is best thought of as a limit to the number of Tweets to return because suspended or deleted content is removed after the count has been applied.
 * @param sinceId Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occurred since the since_id, the since_id will be forced to the oldest ID available.
 * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
 * @param includeEntities The entities node will be omitted when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Favorites] endpoint instance.
 * @return [JsonGeneralApiAction] for [Status] model.
 */
public fun <T> Favorites.list(
    deserializer: DeserializationStrategy<T>,
    count: Int? = null,
    sinceId: Long? = null,
    maxId: Long? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listInternal(deserializer, null, null, count, sinceId, maxId, includeEntities, *options)

public inline fun <reified T> Favorites.list(
    count: Int? = null,
    sinceId: Long? = null,
    maxId: Long? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = list(deserializer(), count, sinceId, maxId, includeEntities, *options)

    /**
 * Note: favorites are now known as likes.
 * Returns the 20 most recent Tweets liked by the authenticating or specified user.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-favorites-list)
 *
 * @param userId The ID of the user for whom to return results.
 * @param count Specifies the number of records to retrieve. Must be less than or equal to 200; defaults to 20. The value of count is best thought of as a limit to the number of Tweets to return because suspended or deleted content is removed after the count has been applied.
 * @param sinceId Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occurred since the since_id, the since_id will be forced to the oldest ID available.
 * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
 * @param includeEntities The entities node will be omitted when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Favorites] endpoint instance.
 * @return [JsonGeneralApiAction] for [Status] model.
 */
public fun <T> Favorites.listByUserId(
    deserializer: DeserializationStrategy<T>,
    userId: Long,
    count: Int? = null,
    sinceId: Long? = null,
    maxId: Long? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listInternal(deserializer, userId, null, count, sinceId, maxId, includeEntities, *options)

public inline fun <reified T> Favorites.listByUserId(
    userId: Long,
    count: Int? = null,
    sinceId: Long? = null,
    maxId: Long? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listByUserId(deserializer(), userId, count, sinceId, maxId, includeEntities, *options)

    /**
 * Note: favorites are now known as likes.
 * Returns the 20 most recent Tweets liked by the authenticating or specified user.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-favorites-list)
 *
 * @param screenName The screen name of the user for whom to return results.
 * @param count Specifies the number of records to retrieve. Must be less than or equal to 200; defaults to 20. The value of count is best thought of as a limit to the number of Tweets to return because suspended or deleted content is removed after the count has been applied.
 * @param sinceId Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occurred since the since_id, the since_id will be forced to the oldest ID available.
 * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
 * @param includeEntities The entities node will be omitted when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Favorites] endpoint instance.
 * @return [JsonGeneralApiAction] for [Status] model.
 */
public fun <T> Favorites.listByScreenName(
    deserializer: DeserializationStrategy<T>,
    screenName: String,
    count: Int? = null,
    sinceId: Long? = null,
    maxId: Long? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listInternal(deserializer, null, screenName, count, sinceId, maxId, includeEntities, *options)

public inline fun <reified T> Favorites.listByScreenName(
    screenName: String,
    count: Int? = null,
    sinceId: Long? = null,
    maxId: Long? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listByScreenName(deserializer(), screenName, count, sinceId, maxId, includeEntities, *options)

private fun <T> Favorites.listInternal(
    deserializer: DeserializationStrategy<T>,
    userId: Long? = null,
    screenName: String? = null,
    count: Int? = null,
    sinceId: Long? = null,
    maxId: Long? = null,
    includeEntities: Boolean? = null,
    vararg options: Option
) = client.session.get("/1.1/favorites/list.json") {
    parameters(
        "user_id" to userId,
        "screen_name" to screenName,
        "count" to count,
        "since_id" to sinceId,
        "max_id" to maxId,
        "include_entities" to includeEntities,
        *options
    )
}.json(deserializer)
