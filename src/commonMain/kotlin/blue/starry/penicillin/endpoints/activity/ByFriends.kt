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

package blue.starry.penicillin.endpoints.activity

import blue.starry.penicillin.core.emulation.EmulationMode
import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.Activity
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Unknown endpoint.
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [Activity] endpoint instance.
 * @return [JsonArrayApiAction] for [ActivityEvent] model.
 */
public fun <T> Activity.byFriends(
    deserializer: DeserializationStrategy<T>,
    count: Int? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.get("/1.1/activity/by_friends.json") {
    emulationModes += EmulationMode.Tweetdeck

    parameters(
        "count" to (count ?: 40),
        "skip_aggregation" to true,
        "cards_platform" to "Web-13",
        "include_entities" to 1,
        "include_user_entities" to 1,
        "include_cards" to 1,
        "send_error_codes" to 1,
        "tweet_mode" to "extended",
        "include_ext_alt_text" to true,
        "include_reply_count" to true,
        *options,
        mode = EmulationMode.Tweetdeck
    )
}.json(deserializer)

public inline fun <reified T> Activity.byFriends(
    count: Int? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = byFriends(deserializer(), count, *options)
