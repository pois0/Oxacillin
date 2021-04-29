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
import jp.pois.oxacillin.endpoints.directmessages.events.create

/**
 * Publishes a new message_create event resulting in a Direct Message sent to a specified user from the authenticating user. Returns an event if successful. Supports publishing Direct Messages with optional Quick Reply and media attachment. Replaces behavior currently provided by [POST direct_messages/new](https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/new-event).
 * Requires a JSON POST body and Content-Type header to be set to application/json. Setting Content-Length may also be required if it is not automatically.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/new-event)
 * 
 * @param userId The ID of the user who should receive the direct message.
 * @param text The Message Data Object defining the content to deliver to the recipient.
 * @param type The type of event you are posting. For Direct Messages, use message_create.
 * @param options Optional. Custom parameters of this request.
 * @receiver [DirectMessageEvents] endpoint instance.
 * @return [JsonGeneralApiAction] for [DirectMessageEvent.Show] model.
 */
public inline fun DirectMessageEvents.create(
    userId: Long,
    text: String,
    type: String = "message_create",
    vararg options: Option
): JsonGeneralApiAction<DirectMessageEvent.Show> = client.directMessageEvents.create(userId, text, type, *options)
