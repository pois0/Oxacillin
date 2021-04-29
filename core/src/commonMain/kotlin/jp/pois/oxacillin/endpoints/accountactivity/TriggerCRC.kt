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

package jp.pois.oxacillin.endpoints.accountactivity

import jp.pois.oxacillin.core.request.action.EmptyApiAction
import jp.pois.oxacillin.core.request.empty
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.put
import jp.pois.oxacillin.endpoints.AccountActivity
import jp.pois.oxacillin.endpoints.Option

/**
 * Triggers the challenge response check (CRC) for the given environments webhook for all activities. If the check is successful, returns 204 and re-enables the webhook by setting its status to valid.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/subscribe-account-activity/api-reference/aaa-premium#put-account-activity-all-env-name-webhooks-webhook-id)
 *
* @param envName Environment name.
 * @param webhookId Webhook id.
 * @param options Optional. Custom parameters of this request.
 * @receiver [AccountActivity] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun AccountActivity.triggerCRC(
    envName: String,
    webhookId: String,
    vararg options: Option
): EmptyApiAction = client.session.put("/1.1/account_activity/all/$envName/webhooks/$webhookId.json") {
    parameters(*options)
}.empty()
