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

package jp.pois.oxacillin.endpoints.help

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Help
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns [Twitter's Privacy Policy](http://twitter.com/privacy).
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/developer-utilities/privacy-policy/api-reference/get-help-privacy)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [Help] endpoint instance.
 * @return [JsonGeneralApiAction] for [Privacy] model.
 */
public fun <T> Help.privacy(
    deserializer: DeserializationStrategy<T>,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.get("/1.1/help/privacy.json") {
    parameters(*options)
}.json(deserializer)

public inline fun <reified T> Help.privacy(
    vararg options: Option
): JsonGeneralApiAction<T> = privacy(deserializer(), *options)
