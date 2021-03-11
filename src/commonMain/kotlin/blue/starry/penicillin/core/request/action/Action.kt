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

package blue.starry.penicillin.core.request.action

import blue.starry.penicillin.core.exceptions.PenicillinException
import blue.starry.penicillin.core.exceptions.throwApiError
import blue.starry.penicillin.core.i18n.LocalizedString
import blue.starry.penicillin.core.request.url
import blue.starry.penicillin.core.util.castOrNull
import blue.starry.penicillin.core.util.getOrNull
import blue.starry.penicillin.extensions.session
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import mu.KotlinLogging

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
