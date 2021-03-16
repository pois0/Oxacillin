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

package blue.starry.penicillin.endpoints.help

import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.Help
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.models.Help.Configuration

/**
 * Returns the current configuration used by Twitter including twitter.com slugs which are not usernames, maximum photo resolutions, and t.co shortened URL length.
 * It is recommended applications request this endpoint when they are loaded, but no more than once a day.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/developer-utilities/configuration/api-reference/get-help-configuration)
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [Help] endpoint instance.
 * @return [JsonGeneralApiAction] for [Configuration] model.
 */
public fun Help.configuration(
    vararg options: Option
): JsonGeneralApiAction<Configuration> = client.session.get("/1.1/help/configuration.json") {
    parameters(*options)
}.jsonObject { Configuration(it, client) }

/**
 * Shorthand property to [Help.configuration].
 * @see Help.configuration
 */
public val Help.configuration: JsonGeneralApiAction<Configuration>
    get() = configuration()
