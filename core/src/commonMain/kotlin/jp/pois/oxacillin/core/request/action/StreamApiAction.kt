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

package jp.pois.oxacillin.core.request.action

import io.ktor.client.call.receive
import io.ktor.client.request.request
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.request
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readUTF8Line
import jp.pois.oxacillin.core.request.ApiRequest
import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.core.streaming.handler.StreamHandler
import jp.pois.oxacillin.core.streaming.listener.StreamListener
import jp.pois.oxacillin.utils.myJson
import kotlinx.serialization.decodeFromString

/**
 * The [ApiAction] that provides stream-able response.
 */
@Suppress("SpellCheckingInspection")
public abstract class StreamApiAction<L: StreamListener, out H: StreamHandler<L>> internal constructor(
    /**
     * The [ApiClient].
     */
    public val client: ApiClient,

    /**
     * The [ApiRequest].
     */
    public val request: ApiRequest
) {
    protected abstract fun getHandler(listener: L): H

    /**
     * Listens with listener and default handler.
     *
     * @param listener [StreamListener].
     */
    public suspend fun listen(listener: L) {
        client.session.httpClient.request<HttpStatement>(request.builder.finalize()).execute {
            checkError(it.request, it)

            val channel = it.receive<ByteReadChannel>()
            handle(channel, listener)
        }
    }

    private suspend fun handle(channel: ByteReadChannel, listener: L) {
        val handler = getHandler(listener)

        try {
            listener.onConnect()

            while (!channel.isClosedForRead) {
                val line = channel.readUTF8Line() ?: continue
                val content = line.unescapeHTML()

                when {
                    content.startsWith('{') -> {
                        handler.handle(myJson.decodeFromString(content))
                    }
                    content.isBlank() -> {
                        listener.onHeartbeat()
                    }
                    else -> {
                        val length = content.toIntOrNull()
                        if (length != null) {
                            listener.onLength(length)
                        } else {
                            listener.onUnknownData(content)
                        }
                    }
                }

                listener.onRawData(content)
            }

            listener.onDisconnect(null)
        } catch (t: Throwable) {
            listener.onDisconnect(t)
        }
    }
}
