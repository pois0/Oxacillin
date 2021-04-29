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

@file:Suppress("Unused")

package jp.pois.oxacillin.extensions.endpoints

import jp.pois.oxacillin.core.request.action.ApiAction
import jp.pois.oxacillin.core.response.JsonGeneralResponse
import jp.pois.oxacillin.endpoints.Lists
import jp.pois.oxacillin.endpoints.lists.*
import jp.pois.oxacillin.extensions.models.CursorUsers
import jp.pois.oxacillin.extensions.models.TwitterListImpl
import jp.pois.oxacillin.extensions.models.User
import jp.pois.oxacillin.extensions.models.untilLast
import jp.pois.oxacillin.extensions.utils.deserializer
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlinx.serialization.DeserializationStrategy

/**
 * Clones this list. The name, description and mode are copied to new list.
 *
 * @param sourceId Source list id.
 *
 * @return New [ApiAction] for new list response.
 */
@OptIn(InternalCoroutinesApi::class)
public fun <T: jp.pois.oxacillin.extensions.TwitterList> Lists.clone(deserializer: DeserializationStrategy<T>, sourceId: Long): ApiAction<JsonGeneralResponse<T>> = delegatedAction {
    val sourceList = show<TwitterListImpl>(sourceId).execute()
    val sourceMembers = members<CursorUsers, User>(sourceId).untilLast("count" to 5000).map { it.id }
    val newList = create<TwitterListImpl>(sourceList.result.name, mode = sourceList.result.mode, description = sourceList.result.description).execute().result

    var list = ArrayList<Long>(100)
    var i = 0
    sourceMembers.collect(object: FlowCollector<Long> {
        override suspend fun emit(value: Long) {
            list[i++] = value
            if (i == 100) {
                addMembersByUserIds(newList.id, list).execute()
                list = ArrayList(100)
                i = 0
            }
        }
    })

    if (i != 0) {
        addMembersByUserIds(newList.id, list.subList(0, i)).execute()
    }

    show(deserializer, newList.id).execute()
}

public inline fun <reified T: jp.pois.oxacillin.extensions.TwitterList> Lists.clone(sourceId: Long): ApiAction<JsonGeneralResponse<T>> = clone(
    deserializer(), sourceId)
