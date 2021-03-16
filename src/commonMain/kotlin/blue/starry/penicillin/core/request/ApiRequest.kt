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

@file:Suppress("NOTHING_TO_INLINE")

package blue.starry.penicillin.core.request

import blue.starry.penicillin.core.request.action.*
import blue.starry.penicillin.core.session.ApiClient
import blue.starry.penicillin.core.streaming.handler.StreamHandler
import blue.starry.penicillin.core.streaming.listener.StreamListener
import blue.starry.penicillin.endpoints.PremiumSearchEnvironment
import blue.starry.penicillin.models.cursor.CursorModel
import kotlinx.serialization.DeserializationStrategy

/**
 * Represents api request methods.
 */
public class ApiRequest(
    /**
     * Current [ApiClient] instance.
     */
    public val client: ApiClient,

    /**
     * Current request builder.
     */
    public val builder: ApiRequestBuilder
) {
    /**
     * Creates [JsonGeneralApiAction] from this request.
     *
     * @return New [JsonGeneralApiAction] for [T].
     */
    public inline fun <T> json(deserializer: DeserializationStrategy<T>): JsonGeneralApiAction<T> {
        return JsonGeneralApiAction(client, this, deserializer)
    }

    /**
     * Creates [CursorJsonApiAction] from this request.
     *
     * @return New [CursorJsonApiAction] for [T].
     */
    public inline fun <M: CursorModel<T>, T: Any> cursorJson(deserializer: DeserializationStrategy<M>): CursorJsonApiAction<M, T> {
        return CursorJsonApiAction(client, this, deserializer)
    }

    /**
     * Creates [PremiumSearchJsonApiAction] from this request.
     *
     * @return New [PremiumSearchJsonApiAction] for [T].
     */
    public fun <T> premiumSearchJsonObject(environment: PremiumSearchEnvironment, deserializer: DeserializationStrategy<T>): PremiumSearchJsonApiAction<T> {
        return PremiumSearchJsonApiAction(client, this, deserializer, environment)
    }

    /**
     * Creates [TextApiAction] from this request.
     *
     * @return New [TextApiAction].
     */
    public fun text(): TextApiAction {
        return TextApiAction(client, this)
    }

    /**
     * Creates [EmptyApiAction] from this request.
     *
     * @return New [EmptyApiAction].
     */
    public inline fun empty(): EmptyApiAction {
        return EmptyApiAction(client, this)
    }

    /**
     * Creates [StreamApiAction] from this request.
     *
     * @return New [StreamApiAction] for [L] and [H].
     */
    public fun <L: StreamListener, H: StreamHandler<L>> stream(): StreamApiAction<L, H> {
        return StreamApiAction(client, this)
    }
}
