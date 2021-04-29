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

package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.core.exceptions.PenicillinException
import jp.pois.oxacillin.core.i18n.LocalizedString
import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameter
import jp.pois.oxacillin.core.response.JsonGeneralResponse
import jp.pois.oxacillin.extensions.utils.edit
import jp.pois.oxacillin.extensions.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/*
    Next operations
 */

/**
 * Whether if current search result has next page.
 */
public val <T: jp.pois.oxacillin.extensions.Search> JsonGeneralResponse<T>.hasNext: Boolean
    get() = !result.searchMetadata.nextResults.isNullOrBlank()

internal val NextQueryNotFound = LocalizedString("It is the last result of search.", "次の検索結果はありません。")

/**
 * Creates next page api action.
 */
public fun <T: jp.pois.oxacillin.extensions.Search> JsonGeneralResponse<T>.next(deserializer: DeserializationStrategy<T>): JsonGeneralApiAction<T> {
    if (!hasNext) {
        throw PenicillinException(NextQueryNotFound)
    }

    result.searchMetadata.nextResults!!.removePrefix("?").split("&").forEach {
        val (k, v) = it.split("=", limit = 2)
        action.edit {
            parameter(k, v)
        }
    }

    return action.request.json(deserializer)
}

public inline fun <reified T: jp.pois.oxacillin.extensions.Search> JsonGeneralResponse<T>.next(): JsonGeneralApiAction<T> = next(
    deserializer()
)

/*
    Refresh operation
 */

/**
 * Whether if current search result is refreshable.
 */
public val <T: jp.pois.oxacillin.extensions.Search> JsonGeneralResponse<T>.refreshable: Boolean
    get() = !result.searchMetadata.refreshUrl.isNullOrBlank()

private val RefreshUrlNotFound = LocalizedString("It is not refreshable search endpoint.", "更新できる検索結果はありません。")

/**
 * Creates refreshed page api action.
 */
public fun <T: jp.pois.oxacillin.extensions.Search> JsonGeneralResponse<T>.refresh(deserializer: DeserializationStrategy<T>): JsonGeneralApiAction<T> {
    if (!refreshable) {
        throw PenicillinException(RefreshUrlNotFound)
    }

    result.searchMetadata.refreshUrl!!.removePrefix("?").split("&").forEach {
        val (k, v) = it.split("=", limit = 2)
        action.edit {
            parameter(k, v)
        }
    }

    return action.request.json(deserializer)
}

public inline fun <reified T: jp.pois.oxacillin.extensions.Search> JsonGeneralResponse<T>.refresh(): JsonGeneralApiAction<T> = refresh(
    deserializer()
)
