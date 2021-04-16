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

package jp.pois.oxacillin.extensions.endpoints

import jp.pois.oxacillin.core.request.action.ApiAction
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Statuses
import jp.pois.oxacillin.endpoints.cards
import jp.pois.oxacillin.endpoints.cards.create
import jp.pois.oxacillin.endpoints.statuses.create
import jp.pois.oxacillin.extensions.utils.deserializer
import jp.pois.oxacillin.extensions.utils.myJson
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

/**
 * Creates new poll tweet.
 *
 * **Warning: This endpoint is private endpoint. So if you use this endpoint, your account can be banned.**
 *
 * @param status Status body.
 * @param choices A list of choices. Max size is 4.
 * @param minutes Duration minutes. Default 1440 mins (1 day).
 * @param options Optional. Custom parameters of this request.
 * @receiver [Statuses] endpoint instance.
 * @return [ApiAction] for [Status] model.
 */
public fun <T> Statuses.createPollTweet(
    deserializer: DeserializationStrategy<T>,
    status: String,
    choices: List<String>,
    minutes: Int = 1440,
    vararg options: Option
): ApiAction<T> = delegatedAction {
    val cardData = buildJsonObject {
        choices.forEachIndexed { i, choice ->
            put("twitter:string:choice${i + 1}_label", choice)
        }
        put("twitter:api:api:endpoint", "1")
        put("twitter:card", "poll${choices.size}choice_text_only")
        put("twitter:long:duration_minutes", minutes)
    }

    val card = client.cards.create<Card>(cardData = myJson.encodeToString(cardData)).execute()
    
    create(deserializer, status, cardUri = card.result.cardUri, options = options).execute().result
}

public inline fun <reified T> Statuses.createPollTweet(
    status: String,
    choices: List<String>,
    minutes: Int = 1440,
    vararg options: Option
): ApiAction<T> = createPollTweet(deserializer(), status, choices, minutes, *options)

@Serializable
private class Card(@SerialName("card_uri") val cardUri: String)
