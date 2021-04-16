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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.pois.oxacillin.defaults.endpoints.directmessages

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.defaults.endpoints.DirectMessages
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.defaults.endpoints.directMessageDeprecatedMessage
import jp.pois.oxacillin.defaults.models.DirectMessage
import jp.pois.oxacillin.endpoints.directMessages

/**
 * Abolished endpoint.
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [DirectMessages] endpoint instance.
 * @return [JsonGeneralApiAction] for [DirectMessage] model.
 */
@Suppress("DEPRECATION")
@Deprecated(directMessageDeprecatedMessage, replaceWith = ReplaceWith("directMessageEvent.delete", "jp.pois.oxacillin.endpoints.directMessageEvent", "jp.pois.oxacillin.defaults.endpoints.directmessages.events.delete"))
public inline fun DirectMessages.delete(
    id: Long,
    includeEntities: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<DirectMessage> = client.directMessages.delete(id, includeEntities, *options)
