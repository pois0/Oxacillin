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

package jp.pois.oxacillin.endpoints.lists

import jp.pois.oxacillin.core.request.action.EmptyApiAction
import jp.pois.oxacillin.core.request.empty
import jp.pois.oxacillin.core.request.formBody
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Lists
import jp.pois.oxacillin.endpoints.Option

/**
 * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Note that lists can't have more than 5,000 members, and you are limited to adding up to 100 members to a list at a time with this method.
 * Please note that there can be issues with lists that rapidly remove and add memberships. Take care when using these methods such that you are not too rapidly switching between removals and adds on the same list.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-members-create_all)
 * 
 * @param listId The numerical id of the list.
 * @param userIds A list of user IDs, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Lists.addMembersByUserIds(
    listId: Long,
    userIds: List<Long>,
    vararg options: Option
): EmptyApiAction = addMembers(listId, null, null, null, userIds, null, *options)

/**
 * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Note that lists can't have more than 5,000 members, and you are limited to adding up to 100 members to a list at a time with this method.
 * Please note that there can be issues with lists that rapidly remove and add memberships. Take care when using these methods such that you are not too rapidly switching between removals and adds on the same list.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-members-create_all)
 *
 * @param listId The numerical id of the list.
 * @param screenNames A list of screen names, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Lists.addMembersByScreenNames(
    listId: Long,
    screenNames: List<String>,
    vararg options: Option
): EmptyApiAction = addMembers(listId, null, null, null, null, screenNames, *options)

/**
 * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Note that lists can't have more than 5,000 members, and you are limited to adding up to 100 members to a list at a time with this method.
 * Please note that there can be issues with lists that rapidly remove and add memberships. Take care when using these methods such that you are not too rapidly switching between removals and adds on the same list.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-members-create_all)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerScreenName The screen name of the user who owns the list being requested by a slug.
 * @param userIds A list of user IDs, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Lists.addMembersByUserIds(
    slug: String,
    ownerScreenName: String,
    userIds: List<Long>,
    vararg options: Option
): EmptyApiAction = addMembers(null, slug, ownerScreenName, null, userIds, null, *options)

/**
 * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Note that lists can't have more than 5,000 members, and you are limited to adding up to 100 members to a list at a time with this method.
 * Please note that there can be issues with lists that rapidly remove and add memberships. Take care when using these methods such that you are not too rapidly switching between removals and adds on the same list.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-members-create_all)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerScreenName The screen name of the user who owns the list being requested by a slug.
 * @param screenNames A list of screen names, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Lists.addMembersByScreenNames(
    slug: String,
    ownerScreenName: String,
    screenNames: List<String>,
    vararg options: Option
): EmptyApiAction = addMembers(null, slug, ownerScreenName, null, null, screenNames, *options)

/**
 * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Note that lists can't have more than 5,000 members, and you are limited to adding up to 100 members to a list at a time with this method.
 * Please note that there can be issues with lists that rapidly remove and add memberships. Take care when using these methods such that you are not too rapidly switching between removals and adds on the same list.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-members-create_all)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerId The user ID of the user who owns the list being requested by a slug.
 * @param userIds A list of user IDs, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Lists.addMembersByUserIds(
    slug: String,
    ownerId: Long,
    userIds: List<Long>,
    vararg options: Option
): EmptyApiAction = addMembers(null, slug, null, ownerId, userIds, null, *options)

/**
 * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Note that lists can't have more than 5,000 members, and you are limited to adding up to 100 members to a list at a time with this method.
 * Please note that there can be issues with lists that rapidly remove and add memberships. Take care when using these methods such that you are not too rapidly switching between removals and adds on the same list.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-members-create_all)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerId The user ID of the user who owns the list being requested by a slug.
 * @param screenNames A list of screen names, up to 100 are allowed in a single request.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [EmptyApiAction].
 */
public fun Lists.addMembersByScreenNames(
    slug: String,
    ownerId: Long,
    screenNames: List<String>,
    vararg options: Option
): EmptyApiAction = addMembers(null, slug, null, ownerId, null, screenNames, *options)

private fun Lists.addMembers(
    listId: Long? = null,
    slug: String? = null,
    ownerScreenName: String? = null,
    ownerId: Long? = null,
    userIds: List<Long>? = null,
    screenNames: List<String>? = null,
    vararg options: Option
) = client.session.post("/1.1/lists/members/create_all.json") {
    formBody(
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "user_id" to userIds?.joinToString(","),
        "screen_name" to screenNames?.joinToString(","),
        *options
    )
}.empty()
