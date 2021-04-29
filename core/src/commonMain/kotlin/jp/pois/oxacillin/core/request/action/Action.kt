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

package jp.pois.oxacillin.core.request.action

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import jp.pois.oxacillin.core.exceptions.PenicillinException
import jp.pois.oxacillin.core.exceptions.throwApiError
import jp.pois.oxacillin.core.i18n.LocalizedString
import jp.pois.oxacillin.core.request.url
import jp.pois.oxacillin.core.session
import jp.pois.oxacillin.utils.castOrNull
import jp.pois.oxacillin.utils.getOrNull
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.*
import mu.KotlinLogging
import kotlin.time.Duration

/**
 * Awaits api execution and returns its result with timeout.
 * This function is suspend-function.
 *
 * @param timeout Timeout duration.
 *
 * @return Api result as [R]. If the timeout exceeds, returns null.
 *
 * @throws PenicillinException General Penicillin exceptions.
 * @throws CancellationException Thrown when coroutine scope is cancelled.
 */
public suspend fun <R: Any> ApiAction<R>.executeWithTimeout(timeout: Duration): R? {
    return withTimeoutOrNull(timeout) {
        execute()
    }
}

private val apiActionLogger = KotlinLogging.logger("Penicillin.ApiAction")

internal suspend fun ApiAction<*>.doRequest(): HttpResponse {
    try {
        return session.httpClient.request<HttpStatement>(request.builder.finalize()).execute()
    } catch (e: CancellationException) {
        throw e
    } catch (t: Throwable) {
        throw PenicillinException(LocalizedString.ApiRequestFailed, t, null, null, request.builder.url)
    }
}

internal fun checkError(request: HttpRequest, response: HttpResponse, content: String? = null) {
    apiActionLogger.trace {
        buildString {
            append("${response.version} ${response.status.value} ${request.method.value} ${request.url}\n")

            val (requestHeaders, responseHeaders) = request.headers.flattenEntries() to response.headers.flattenEntries()
            val (longestRequestHeaderLength, longestResponseHeaderLength) = requestHeaders.maxByOrNull { it.first.length }?.first.orEmpty().length + 1 to responseHeaders.maxByOrNull { it.first.length }?.first.orEmpty().length + 1
            append("Request headers =\n${requestHeaders.joinToString("\n") { "    ${it.first.padEnd(longestRequestHeaderLength)}: ${it.second}" }}\n")
            append("Response headers =\n${responseHeaders.joinToString("\n") { "    ${it.first.padEnd(longestResponseHeaderLength)}: ${it.second}" }}\n\n")

            append(
                when {
                    content == null -> {
                        "(Streaming Response)"
                    }
                    content.isBlank() -> {
                        "(Empty Response)"
                    }
                    else -> {
                        content
                    }
                }
            )
        }
    }

    if (response.status.isSuccess()) {
        return
    }

    content?.let(Json::parseToJsonElement)?.let { json ->
        when (val error = json.getOrNull("errors")?.castOrNull<JsonArray>()?.firstOrNull() ?: json.getOrNull("error")) {
            is JsonObject -> {
                val code = error["errors"]?.castOrNull<JsonPrimitive>()?.intOrNull
                val message = error["message"]?.castOrNull<JsonPrimitive>()?.toString()

                throwApiError(code, message.orEmpty(), content, request, response)
            }
            is JsonPrimitive -> {
                throwApiError(null, error.toString(), content, request, response)
            }
            else -> {
                throw PenicillinException(LocalizedString.UnknownApiErrorWithStatusCode, null, request, response, response.status.value, content)
            }
        }
    }

    throw PenicillinException(LocalizedString.ApiReturnedNon200StatusCode, null, request, response, response.status.value, response.status.description)
}

internal suspend inline fun HttpResponse.readTextOrNull(): String? {
    return runCatching { 
        readText().unescapeHTML()
    }.onFailure {
        apiActionLogger.debug(it) { "Could not readText." }
    }.getOrNull()
}

internal fun String.unescapeHTML(): String {
    return replace("&amp;", "&").replace("&lt;", "<").replace("&gt;", ">")
}
