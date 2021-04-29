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

package jp.pois.oxacillin.core.streaming.handler

import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.core.streaming.listener.FilterStreamListener
import jp.pois.oxacillin.utils.myJson
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonObject

/**
 * Default FilterStream [StreamHandler].
 * Accepts listener of [FilterStreamListener].
 */
public class FilterStreamHandler<STATUS, DELETE, WARNING>(
    override val client: ApiClient,
    override val listener: FilterStreamListener<STATUS, DELETE, WARNING>,
    private val statusDeserializer: DeserializationStrategy<STATUS>,
    private val deleteDeserializer: DeserializationStrategy<DELETE>,
    private val warningDeserializer: DeserializationStrategy<WARNING>
): StreamHandler<FilterStreamListener<STATUS, DELETE, WARNING>> {
    override suspend fun handle(json: JsonObject) {
        when {
            "text" in json -> {
                listener.onStatus(myJson.decodeFromJsonElement(statusDeserializer, json))
            }
            "delete" in json -> {
                listener.onDelete(myJson.decodeFromJsonElement(deleteDeserializer, json))
            }
            "warning" in json -> {
                listener.onWarning(myJson.decodeFromJsonElement(warningDeserializer, json))
            }
            else -> {
                listener.onUnhandledJson(json)
            }
        }

        listener.onAnyJson(json)
    }
}
