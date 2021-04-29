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

package jp.pois.oxacillin.endpoints.welcomemessages.rules

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.WelcomeMessageRules
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns a list of Welcome Message Rules.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/direct-messages/welcome-messages/api-reference/list-welcome-message-rules)
 * 
 * @param count Number of welcome message rules to be returned. Max of 50. Default is 20.
 * @param cursor For paging through result sets greater than 1 page, use the “next_cursor” property from the previous request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [WelcomeMessageRules] endpoint instance.
 * @return [JsonGeneralApiAction] for [WelcomeMessageRule.List] model.
 */
public fun <T> WelcomeMessageRules.list(
    deserializer: DeserializationStrategy<T>,
    count: Int? = null,
    cursor: String? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.get("/1.1/direct_messages/welcome_messages/rules/list.json") {
    parameters(
        "count" to count,
        "cursor" to cursor,
        *options
    )
}.json(deserializer)

public inline fun <reified T> WelcomeMessageRules.list(
    count: Int? = null,
    cursor: String? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = list(deserializer(), count, cursor, *options)
