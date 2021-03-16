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

import blue.starry.penicillin.core.request.action.JsonObjectApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.AccountActivity
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.models.Webhook

/**
 * Returns all environments, webhook URLs and their statuses for the authenticating app. Currently, only one webhook URL can be registered to each environment.
 * We mark a URL as invalid if it fails the daily validation check. In order to re-enable the URL, call the update endpoint.
 * Alternatively, this endpoint can be used with an environment name to only return webhook URLS for the given environment: GET account_activity/all/:env_name/webhooks (see second example)
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/subscribe-account-activity/api-reference/aaa-premium#get-account-activity-all-webhooks)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [AccountActivity] endpoint instance.
 * @return [JsonObjectApiAction] for [Webhook.List] model.
 */
public fun AccountActivity.listWebhooks(
    vararg options: Option
): JsonObjectApiAction<Webhook.List> = client.session.get("/1.1/account_activity/all/webhooks.json") {
    parameters(*options)
}.jsonObject { Webhook.List(it, client) }


/**
 * Shorthand property to [AccountActivity.listWebhooks].
 * @see AccountActivity.listWebhooks
 */
public val AccountActivity.listWebhooks: JsonObjectApiAction<Webhook.List>
    get() = listWebhooks()

/**
 * Returns all environments, webhook URLs and their statuses for the authenticating app. Currently, only one webhook URL can be registered to each environment.
 * We mark a URL as invalid if it fails the daily validation check. In order to re-enable the URL, call the update endpoint.
 * Alternatively, this endpoint can be used with an environment name to only return webhook URLS for the given environment: GET account_activity/all/:env_name/webhooks (see second example)
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/subscribe-account-activity/api-reference/aaa-premium#get-account-activity-all-webhooks)
 *
 * @param envName Environment name.
 * @param options Optional. Custom parameters of this request.
 * @receiver [AccountActivity] endpoint instance.
 * @return [JsonObjectApiAction] for [Webhook.List] model.
 */
public fun AccountActivity.listWebhooksByEnvName(
    envName: String, vararg options: Option
): JsonArrayApiAction<Webhook.Model> = client.session.get("/1.1/account_activity/all/$envName/webhooks.json") {
    parameters(*options)
}.jsonArray { Webhook.Model(it, client) }
