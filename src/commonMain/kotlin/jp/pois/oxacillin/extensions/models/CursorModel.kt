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

package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.core.exceptions.PenicillinException
import jp.pois.oxacillin.core.i18n.LocalizedString
import jp.pois.oxacillin.core.request.action.ApiAction
import jp.pois.oxacillin.core.request.action.CursorJsonApiAction
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.response.CursorJsonObjectResponse
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.core.response.awaitRefresh
import jp.pois.oxacillin.extensions.util.edit
import jp.pois.oxacillin.core.response.isExceeded
import jp.pois.oxacillin.core.response.rateLimit
import jp.pois.oxacillin.models.CursorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

/*
    Next operations
 */

/**
 * Next cursor value.
 */
public val <M: CursorModel<T>, T : Any> CursorJsonObjectResponse<M, T>.nextCursor: Long
    get() = result.nextCursor
/**
 * If true, next cursor exists.
 */
public val <M: CursorModel<T>, T : Any> CursorJsonObjectResponse<M, T>.hasNext: Boolean
    get() = nextCursor > 0
/**
 * New [ApiAction] with next cursor.
 */
public val <M: CursorModel<T>, T : Any> CursorJsonObjectResponse<M, T>.next: CursorJsonApiAction<M, T>
    get() = byCursor(nextCursor)

/*
    Previous operations
 */

/**
 * Previous cursor value.
 */
public val <M: CursorModel<T>, T : Any> CursorJsonObjectResponse<M, T>.previousCursor: Long
    get() = result.previousCursor
/**
 * If true, previous cursor exists.
 */
public val <M: CursorModel<T>, T : Any> CursorJsonObjectResponse<M, T>.hasPrevious: Boolean
    get() = previousCursor > 0
/**
 * New [ApiAction] with previous cursor.
 */
public val <M: CursorModel<T>, T: Any> CursorJsonObjectResponse<M, T>.previous: CursorJsonApiAction<M, T>
    get() = byCursor(previousCursor)

/*
    Paging
 */

/**
 * New [ApiAction] with specified cursor.
 *
 * @param cursor Cursor value.
 * @param options options Optional. Custom parameters of this request.
 */
public fun <M: CursorModel<T>, T: Any> CursorJsonObjectResponse<M, T>.byCursor(cursor: Long, vararg options: Option): CursorJsonApiAction<M, T> {
    if (cursor == 0L) {
        throw PenicillinException(LocalizedString.CursorIsZero, null, request, response)
    }

    action.edit {
        parameters("cursor" to cursor, *options)
    }

    return CursorJsonApiAction(client, action.request, action.deserializer)
}

/**
 * Retrieves all the responses with current [ApiAction].
 *
 * @param options options Optional. Custom parameters of this request.
 */
public fun <M: CursorModel<T>, T: Any> CursorJsonApiAction<M, T>.untilLast(vararg options: Option): Flow<T> = flow {
    val first = execute()
    emitAll(first.result.items.asFlow())

    var cursor = first.nextCursor
    while (cursor != 0L) {
        val response = first.byCursor(cursor, *options).execute()
        emitAll(response.result.items.asFlow())

        cursor = response.nextCursor

        val rateLimit = response.rateLimit ?: continue
        if (rateLimit.isExceeded) {
            rateLimit.awaitRefresh()
        }
    }
}

/**
 * Retrieves all the responses with current [ApiAction].
 *
 * @param options options Optional. Custom parameters of this request.
 */
public fun <M: CursorModel<T>, T: Any> CursorJsonObjectResponse<M, T>.untilLast(vararg options: Option): Flow<T> = flow {
    emitAll(result.items.asFlow())

    if (hasNext) {
        emitAll(next.untilLast(*options))
    }
}
