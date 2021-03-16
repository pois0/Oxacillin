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

package blue.starry.penicillin.extensions.cursor

import blue.starry.penicillin.core.exceptions.PenicillinException
import blue.starry.penicillin.core.i18n.LocalizedString
import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.response.JsonGeneralResponse
import blue.starry.penicillin.extensions.edit
import blue.starry.penicillin.models.Search

/*
    Next operations
 */

/**
 * Whether if current search result has next page.
 */
public val JsonGeneralResponse<Search>.hasNext: Boolean
    get() = !result.searchMetadata.nextResults.isNullOrBlank()

internal val NextQueryNotFound = LocalizedString("It is the last result of search.", "次の検索結果はありません。")

/**
 * Creates next page api action.
 */
public val JsonGeneralResponse<Search>.next: JsonGeneralApiAction<Search>
    get() {
        if (!hasNext) {
            throw PenicillinException(NextQueryNotFound)
        }

        result.searchMetadata.nextResults!!.removePrefix("?").split("&").map {
            it.split("=", limit = 2)
        }.forEach { (k, v) ->
            action.edit {
                parameters(k to v)
            }
        }

        return action.request.jsonObject { Search(it, client) }
    }

/*
    Refresh operation
 */

/**
 * Whether if current search result is refreshable.
 */
public val JsonGeneralResponse<Search>.refreshable: Boolean
    get() = !result.searchMetadata.refreshUrl.isNullOrBlank()

private val RefreshUrlNotFound = LocalizedString("It is not refreshable search endpoint.", "更新できる検索結果はありません。")

/**
 * Creates refreshed page api action.
 */
public val JsonGeneralResponse<Search>.refresh: JsonGeneralApiAction<Search>
    get() {
        if (!refreshable) {
            throw PenicillinException(RefreshUrlNotFound)
        }

        result.searchMetadata.refreshUrl!!.removePrefix("?").split("&").map {
            it.split("=", limit = 2)
        }.forEach { (k, v) ->
            action.edit {
                parameters(k to v)
            }
        }

        return action.request.jsonObject { Search(it, client) }
    }
