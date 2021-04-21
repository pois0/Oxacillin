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

package jp.pois.oxacillin.defaults.endpoints.statuses

import jp.pois.oxacillin.core.request.action.JsonArrayApiAction
import jp.pois.oxacillin.defaults.endpoints.Statuses
import jp.pois.oxacillin.defaults.models.Status
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.common.TweetMode
import jp.pois.oxacillin.endpoints.statuses
import jp.pois.oxacillin.endpoints.statuses.retweets

/**
 * Returns a collection of the 100 most recent retweets of the Tweet specified by the id parameter.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-statuses-retweets-id)
 * 
 * @param id The numerical ID of the desired status.
 * @param count Specifies the number of records to retrieve. Must be less than or equal to 100.
 * @param trimUser When set to either true , t or 1 , each tweet returned in a timeline will include a user object including only the status authors numerical ID. Omit this parameter to receive the complete user object.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Statuses] endpoint instance.
 * @return [JsonArrayApiAction] for [Status] model.
 */
public inline fun Statuses.retweets(
    id: Long,
    count: Int? = null,
    trimUser: Boolean? = null,
    tweetMode: TweetMode = TweetMode.Default,
    vararg options: Option
): JsonArrayApiAction<Status> = client.statuses.retweets(id, count, trimUser, tweetMode, *options)
