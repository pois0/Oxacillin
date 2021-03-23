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

package jp.pois.oxacillin.endpoints.welcomemessages.rules

import jp.pois.oxacillin.core.request.action.EmptyApiAction
import jp.pois.oxacillin.core.request.empty
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.delete
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.WelcomeMessageRules

/**
 * Deletes a Welcome Message Rule by the given id.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/direct-messages/welcome-messages/api-reference/delete-welcome-message-rule)
 * 
 * @param id The id of the Welcome Message Rule that should be deleted.
 * @param options Optional. Custom parameters of this request.
 * @receiver [WelcomeMessageRules] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun WelcomeMessageRules.delete(
    id: Long,
    vararg options: Option
): EmptyApiAction = client.session.delete("/1.1/direct_messages/welcome_messages/rules/destroy.json") {
    parameters(
        "id" to id,
        *options
    )
}.empty()
