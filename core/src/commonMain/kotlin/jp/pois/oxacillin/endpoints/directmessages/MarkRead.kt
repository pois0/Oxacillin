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

package jp.pois.oxacillin.endpoints.directmessages

import jp.pois.oxacillin.core.request.ApiRequest
import jp.pois.oxacillin.core.request.action.EmptyApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.DirectMessages
import jp.pois.oxacillin.endpoints.Option

/**
 * Marks a message as read in the recipient’s Direct Message conversation view with the sender.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/direct-messages/typing-indicator-and-read-receipts/api-reference/new-read-receipt)
 * 
 * @param lastReadEventId The message ID of the most recent message to be marked read. All messages before it will be marked read as well.
 * @param recipientId The user ID of the user the message is from.
 * @param options Optional. Custom parameters of this request.
 * @receiver [DirectMessages] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun DirectMessages.markRead(
    lastReadEventId: Long,
    recipientId: Long,
    vararg options: Option
): ApiRequest = client.session.post("/1.1/direct_messages/mark_read.json") {
    formBody(
        "last_read_event_id" to lastReadEventId,
        "recipient_id" to recipientId,
        *options
    )
}
