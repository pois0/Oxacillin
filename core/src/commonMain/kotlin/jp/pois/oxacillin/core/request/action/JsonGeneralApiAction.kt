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

import jp.pois.oxacillin.core.exceptions.PenicillinException
import jp.pois.oxacillin.core.i18n.LocalizedString
import jp.pois.oxacillin.core.request.ApiRequest
import jp.pois.oxacillin.core.response.JsonGeneralResponse
import jp.pois.oxacillin.core.session.ApiClient

import io.ktor.client.statement.request
import jp.pois.oxacillin.utils.myJson
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

/**
 * The [ApiAction] that provides parsed json object with json model.
 */
public class JsonGeneralApiAction<T>(
    override val client: ApiClient,
    override val request: ApiRequest,
    private val deserializer: DeserializationStrategy<T>
): ApiAction<JsonGeneralResponse<T>> {
    override suspend fun execute(): JsonGeneralResponse<T> {
        val response = doRequest()
        val request = response.request

        val content = response.readTextOrNull()

        checkError(request, response, content)

        var cause: Throwable? = null

        if (content != null) {
            runCatching {
                myJson.decodeFromString(deserializer, content)
            }.onSuccess {
                return JsonGeneralResponse(client, it, request, response, content.orEmpty(), this)
            }.onFailure {
                cause = it
            }
        }

        throw PenicillinException(LocalizedString.JsonModelCastFailed, cause, request, response, content)
    }
}
