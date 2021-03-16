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

package blue.starry.penicillin.endpoints.accountactivity

import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.AccountActivity
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.models.Subscription

/**
 * Returns the count of subscriptions that are currently active on your account for all activities. Note that the /count endpoint requires application-only OAuth, so that you should make requests using a bearer token instead of user context.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/subscribe-account-activity/api-reference/aaa-premium#get-account-activity-all-subscriptions-count)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [AccountActivity] endpoint instance.
 * @return [JsonGeneralApiAction] for [Subscription.Count] model.
 */
public fun AccountActivity.subscriptionCount(
    vararg options: Option
): JsonGeneralApiAction<Subscription.Count> = client.session.get("/1.1/account_activity/all/subscriptions/count.json") {
    parameters(*options)
}.jsonObject { Subscription.Count(it, client) }

/**
 * Shorthand property to [AccountActivity.subscriptionCount].
 * @see AccountActivity.subscriptionCount
 */
public val AccountActivity.subscriptionCount: JsonGeneralApiAction<Subscription.Count>
    get() = subscriptionCount()
