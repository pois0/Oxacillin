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

package jp.pois.oxacillin.endpoints.cards

import jp.pois.oxacillin.core.emulation.EmulationMode
import jp.pois.oxacillin.core.request.EndpointHost
import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Cards
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Creates new "card" object. This is what we call polls.
 *
 * @param cardData Required. The card data encoded with JSON.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Cards] endpoint instance.
 * @return [JsonGeneralApiAction] for [Card] model.
 */
public fun <T> Cards.create(
    deserializer: DeserializationStrategy<T>,
    cardData: String,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.post("/v2/cards/create.json", EndpointHost.Card) {
    emulationModes += EmulationMode.TwitterForiPhone

    formBody(
        "card_data" to cardData,
        *options
    )
}.json(deserializer)

public inline fun <reified T> Cards.create(
    cardData: String,
    vararg options: Option
): JsonGeneralApiAction<T> = create(deserializer(), cardData, *options)
