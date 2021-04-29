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

package jp.pois.oxacillin.defaults.endpoints.welcomemessages

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.defaults.endpoints.WelcomeMessages
import jp.pois.oxacillin.defaults.models.WelcomeMessage
import jp.pois.oxacillin.endpoints.welcomeMessages
import jp.pois.oxacillin.endpoints.welcomemessages.update
import kotlinx.serialization.json.JsonObject

/**
 * Updates a Welcome Message by the given ID. Updates to the welcome_message object are atomic. For example, if the Welcome Message currently has quick_reply defined and you only provide text, the quick_reply object will be removed from the Welcome Message.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/direct-messages/welcome-messages/api-reference/new-welcome-message)
 * 
 * @param id The id of the Welcome Message that should be updated.
 * @param messageData The Message Data Object defining the content of the message template. See POST [direct_messages/events/new (message_create)](https://developer.twitter.com/en/docs/direct-messages/sending-and-receiving/api-reference/new-event) for Message Data object details.
 * @param options Optional. Custom parameters of this request.
 * @receiver [WelcomeMessages] endpoint instance.
 * @return [JsonGeneralApiAction] for [WelcomeMessage.Single] model.
 */
public inline fun WelcomeMessages.update(
    id: String,
    messageData: JsonObject,
    vararg options: Option
): JsonGeneralApiAction<WelcomeMessage.Single> = client.welcomeMessages.update(id, messageData, *options)
