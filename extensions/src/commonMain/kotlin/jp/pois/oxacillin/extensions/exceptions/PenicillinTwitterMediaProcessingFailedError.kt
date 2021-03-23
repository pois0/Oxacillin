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

package jp.pois.oxacillin.extensions.exceptions

import jp.pois.oxacillin.core.exceptions.PenicillinException
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponse
import jp.pois.oxacillin.core.i18n.LocalizedString
import jp.pois.oxacillin.extensions.MediaError

private val apiErrorString = LocalizedString(
    "%s (%d): %s (%s)"
)

/**
 * The [PenicillinException] class which is thrown when Twitter API error occurs.
 */
public class PenicillinTwitterMediaProcessingFailedError(
    /**
     * Returned [Media.ProcessingInfo.Error].
     */
    public val error: jp.pois.oxacillin.extensions.MediaError,

    /**
     * Executed [HttpRequest].
     */
    override val request: HttpRequest,

    /**
     * Executed [HttpResponse].
     */
    override val response: HttpResponse
): PenicillinException(apiErrorString, null, request, response, error.name, error.code, error.message, request.url)
