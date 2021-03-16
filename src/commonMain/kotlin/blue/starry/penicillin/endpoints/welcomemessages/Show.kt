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

package blue.starry.penicillin.endpoints.welcomemessages

import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.endpoints.WelcomeMessages
import blue.starry.penicillin.models.WelcomeMessage

/**
 * Returns a Welcome Message by the given id.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/direct-messages/welcome-messages/api-reference/get-welcome-message)
 * 
 * @param id The id of the Welcome Message that should be returned.
 * @param options Optional. Custom parameters of this request.
 * @receiver [WelcomeMessages] endpoint instance.
 * @return [JsonGeneralApiAction] for [WelcomeMessage.Single] model.
 */
public fun WelcomeMessages.show(
    id: Long,
    vararg options: Option
): JsonGeneralApiAction<WelcomeMessage.Single> = client.session.get("/1.1/direct_messages/welcome_messages/show.json") {
    parameters(
        "id" to id,
        *options
    )
}.jsonObject { WelcomeMessage.Single(it, client) }
