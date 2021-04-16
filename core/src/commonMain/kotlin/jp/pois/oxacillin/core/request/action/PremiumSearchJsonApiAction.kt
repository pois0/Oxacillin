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
import jp.pois.oxacillin.core.response.PremiumSearchJsonObjectResponse
import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.endpoints.PremiumSearchEnvironment

import io.ktor.client.statement.request
import jp.pois.oxacillin.utils.myJson
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json

/**
 * The [ApiAction] that provides parsed json array with json model. This class supports premium search api operations.
 */
public class PremiumSearchJsonApiAction<T>(
    override val client: ApiClient,
    override val request: ApiRequest,
    private val deserializer: DeserializationStrategy<T>,

    /**
     * [PremiumSearchEnvironment] which will be used to acquire this response.
     */
    public val environment: PremiumSearchEnvironment
): ApiAction<PremiumSearchJsonObjectResponse<T>> {
    override suspend fun execute(): PremiumSearchJsonObjectResponse<T> {
        val response = doRequest()
        val request = response.request

        val content = response.readTextOrNull()

        checkError(request, response, content)

        var cause: Throwable? = null

        if (content != null) {
            runCatching {
                myJson.decodeFromString(deserializer, content)
            }.onSuccess {
                return PremiumSearchJsonObjectResponse(client, it, request, response, content.orEmpty(), this, environment)
            }.onFailure {
                cause = it
            }
        }

        throw PenicillinException(LocalizedString.JsonModelCastFailed, cause, request, response, content)
    }
}
