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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.pois.oxacillin.defaults.endpoints.directmessages.events

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.DirectMessageEvents
import jp.pois.oxacillin.defaults.models.DirectMessageEvent
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.directMessageEvents
import jp.pois.oxacillin.endpoints.directmessages.events.list

/**
 * Returns all Direct Message events (both sent and received) within the last 30 days. Sorted in reverse-chronological order.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/list-events)
 * 
 * @param count Max number of events to be returned. 20 default. 50 max.
 * @param cursor For paging through result sets greater than 1 page, use the “next_cursor” property from the previous request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [DirectMessageEvents] endpoint instance.
 * @return [JsonGeneralApiAction] for [DirectMessageEvent.List] model.
 */
public inline fun DirectMessageEvents.list(
    count: Int? = null,
    cursor: String? = null,
    vararg options: Option
): JsonGeneralApiAction<DirectMessageEvent.List> = client.directMessageEvents.list(count, cursor, *options)
