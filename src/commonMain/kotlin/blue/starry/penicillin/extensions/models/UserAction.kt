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

package blue.starry.penicillin.extensions.models

import blue.starry.penicillin.core.request.action.CursorJsonApiAction
import blue.starry.penicillin.core.request.action.EmptyApiAction
import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.endpoints.*
import blue.starry.penicillin.models.CommonUser
import blue.starry.penicillin.models.Status
import blue.starry.penicillin.models.User
import blue.starry.penicillin.models.cursor.CursorIds
import blue.starry.penicillin.models.cursor.CursorUsers

/**
 * Creates an action to retrieve this user.
 */
public fun CommonUser.refresh(): JsonGeneralApiAction<User> = client.users.showByUserId(userId = id)

/**
 * Creates an action to follow this user.
 */
public fun CommonUser.follow(): JsonGeneralApiAction<User> = client.friendships.createByUserId(userId = id)

/**
 * Creates an action to unfollow this user.
 */
public fun CommonUser.unfollow(): JsonGeneralApiAction<User> = client.friendships.destroyByUserId(userId = id)

/**
 * Creates an action to mute this user.
 */
public fun CommonUser.mute(): JsonGeneralApiAction<User> = client.mutes.createByUserId(userId = id)

/**
 * Creates an action to unmute this user.
 */
public fun CommonUser.unmute(): JsonGeneralApiAction<User> = client.mutes.destroyByUserId(userId = id)

/**
 * Creates an action to block this user.
 */
public fun CommonUser.block(): JsonGeneralApiAction<User> = client.blocks.createByUserId(userId = id)

/**
 * Creates an action to unblock this user.
 */
public fun CommonUser.unblock(): JsonGeneralApiAction<User> = client.blocks.destroyByUserId(userId = id)

/**
 * Creates an action to report this user as spam.
 */
public fun CommonUser.reportAsSpam(): JsonGeneralApiAction<User> = client.users.reportSpamByUserId(userId = id)

/**
 * Creates an ation to retrieve the user timeline of this user.
 */
public fun CommonUser.timeline(): JsonArrayApiAction<Status> = client.timeline.userTimelineByUserId(userId = id)

/**
 * Creates an action to retrieve friends' (follows') ids of this user.
 */
public fun CommonUser.friendIds(): CursorJsonApiAction<CursorIds, Long> = client.friends.listIdsByUserId(userId = id)

/**
 * Creates an action to retrieve friends' (follows') users of this user.
 */
public fun CommonUser.friendUsers(): CursorJsonApiAction<CursorUsers, User> = client.friends.listUsersByUserId(userId = id)

/**
 * Creates an action to retrieve followers' ids of this user.
 */
public fun CommonUser.followerIds(): CursorJsonApiAction<CursorIds, Long> = client.followers.listIdsByUserId(userId = id)

/**
 * Creates an action to retrieve followers' users of this user.
 */
public fun CommonUser.followerUsers(): CursorJsonApiAction<CursorUsers, User> = client.followers.listUsersByUserId(userId = id)

/**
 * Creates an action to add this user to the list.
 *
 * @param listId The list id.
 */
public fun CommonUser.addToList(listId: Long): EmptyApiAction = client.lists.addMember(listId, userId = id)
/**
 * Creates an action to add this user to the list.
 *
 * @param slug The list slug.
 * @param ownerScreenName The owner screen name of the list.
 */
public fun CommonUser.addToList(slug: String, ownerScreenName: String): EmptyApiAction = client.lists.addMember(slug, ownerScreenName, userId = id)
/**
 * Creates an action to add this user to the list.
 *
 * @param slug The list slug.
 * @param ownerId The owner id of the list.
 */
public fun CommonUser.addToList(slug: String, ownerId: Long): EmptyApiAction = client.lists.addMember(slug, ownerId, userId = id)

/**
 * Creates an action to remove this user from the list.
 *
 * @param listId The list id.
 */
public fun CommonUser.removeFromList(listId: Long): EmptyApiAction = client.lists.removeMember(listId, userId = id)
/**
 * Creates an action to remove this user from the list.
 *
 * @param slug The list slug.
 * @param ownerScreenName The owner screen name of the list.
 */
public fun CommonUser.removeFromList(slug: String, ownerScreenName: String): EmptyApiAction = client.lists.removeMember(slug, ownerScreenName, userId = id)
/**
 * Creates an action to remove this user from the list.
 *
 * @param slug The list slug.
 * @param ownerId The owner id of the list.
 */
public fun CommonUser.removeFromList(slug: String, ownerId: Long): EmptyApiAction = client.lists.removeMember(slug, ownerId, userId = id)
