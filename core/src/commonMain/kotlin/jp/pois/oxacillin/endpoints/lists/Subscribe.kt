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

package jp.pois.oxacillin.endpoints.lists

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Lists
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Subscribes the authenticated user to the specified list.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-subscribers-create)
 * 
 * @param listId The numerical id of the list.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonGeneralApiAction] for [TwitterList] model.
 */
public fun <T> Lists.subscribe(
    deserializer: DeserializationStrategy<T>,
    listId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = subscribe(deserializer, listId, null, null, null, *options)

public inline fun <reified T> Lists.subscribe(
    listId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = subscribe(deserializer(), listId, *options)

/**
 * Subscribes the authenticated user to the specified list.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-subscribers-create)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerScreenName The screen name of the user who owns the list being requested by a slug.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonGeneralApiAction] for [TwitterList] model.
 */
public fun <T> Lists.subscribeByOwnerScreenName(
    deserializer: DeserializationStrategy<T>,
    slug: String,
    ownerScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = subscribe(deserializer, null, slug, ownerScreenName, null, *options)

public inline fun <reified T> Lists.subscribeByOwnerScreenName(
    slug: String,
    ownerScreenName: String,
    vararg options: Option
): JsonGeneralApiAction<T> = subscribeByOwnerScreenName(deserializer(), slug, ownerScreenName, *options)

/**
 * Subscribes the authenticated user to the specified list.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-subscribers-create)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerId The user ID of the user who owns the list being requested by a slug.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonGeneralApiAction] for [TwitterList] model.
 */
public fun <T> Lists.subscribeByOwnerId(
    deserializer: DeserializationStrategy<T>,
    slug: String,
    ownerId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = subscribe(deserializer, null, slug, null, ownerId, *options)

public inline fun <reified T> Lists.subscribeByOwnerId(
    slug: String,
    ownerId: Long,
    vararg options: Option
): JsonGeneralApiAction<T> = subscribeByOwnerId(deserializer(), slug, ownerId, *options)

private fun <T> Lists.subscribe(
    deserializer: DeserializationStrategy<T>,
    listId: Long? = null,
    slug: String? = null,
    ownerScreenName: String? = null,
    ownerId: Long? = null,
    vararg options: Option
) = client.session.post("/1.1/lists/subscribers/create.json") {
    formBody(
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        *options
    )
}.json(deserializer)
