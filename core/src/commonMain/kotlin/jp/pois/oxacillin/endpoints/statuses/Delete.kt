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

package jp.pois.oxacillin.endpoints.statuses

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Statuses
import jp.pois.oxacillin.endpoints.common.TweetMode
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Destroys the status specified by the required ID parameter. The authenticating user must be the author of the specified status. Returns the destroyed status if successful.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-destroy-id)
 * 
 * @param id The numerical ID of the desired status.
 * @param trimUser When set to either true , t or 1 , each tweet returned in a timeline will include a user object including only the status authors numerical ID. Omit this parameter to receive the complete user object.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Statuses] endpoint instance.
 * @return [JsonGeneralApiAction] for [Status] model.
 */
public fun <T> Statuses.delete(
    deserializer: DeserializationStrategy<T>,
    id: Long,
    trimUser: Boolean? = null,
    tweetMode: TweetMode = TweetMode.Default,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.post("/1.1/statuses/destroy/$id.json") {
    formBody(
        "trim_user" to trimUser,
        "tweet_mode" to tweetMode,
        *options
    )
}.json(deserializer)

public inline fun <reified T> Statuses.delete(
    id: Long,
    trimUser: Boolean? = null,
    tweetMode: TweetMode = TweetMode.Default,
    vararg options: Option
): JsonGeneralApiAction<T> = delete(deserializer(), id, trimUser, tweetMode, *options)
