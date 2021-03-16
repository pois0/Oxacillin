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

package blue.starry.penicillin.endpoints.statuses

import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.formBody
import blue.starry.penicillin.core.session.post
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.endpoints.Statuses
import blue.starry.penicillin.endpoints.common.TweetMode
import blue.starry.penicillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Untweets a retweeted status. Returns the original Tweet with Retweet details embedded.
 * 
 * Usage Notes:
 * - This method is subject to update limits. A HTTP 429 will be returned if this limit has been hit.
 * - The untweeted retweet status ID must be authored by the user backing the authentication token.
 * - An application must have write privileges to POST. A HTTP 401 will be returned for read-only applications.
 * - When passing a source status ID instead of the retweet status ID a HTTP 200 response will be returned with the same Tweet object but no action.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-unretweet-id)
 * 
 * @param id The numerical ID of the desired status.
 * @param trimUser When set to either true, t or 1, each Tweet returned in a timeline will include a user object including only the status authors numerical ID. Omit this parameter to receive the complete user object.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Statuses] endpoint instance.
 * @return [JsonGeneralApiAction] for [Status] model.
 */
public fun <T> Statuses.unretweet(
    deserializer: DeserializationStrategy<T>,
    id: Long,
    trimUser: Boolean? = null,
    tweetMode: TweetMode? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.post("/1.1/statuses/unretweet/$id.json") {
    formBody(
        "trim_user" to trimUser,
        "tweet_mode" to tweetMode,
        *options
    )
}.json(deserializer)

public inline fun <reified T> Statuses.unretweet(
    id: Long,
    trimUser: Boolean? = null,
    tweetMode: TweetMode? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = unretweet(deserializer(), id, trimUser, tweetMode, *options)
