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

package jp.pois.oxacillin.endpoints.friends

import jp.pois.oxacillin.core.emulation.EmulationMode
import jp.pois.oxacillin.core.request.action.CursorJsonApiAction
import jp.pois.oxacillin.core.request.cursorJson
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Friends
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.models.CursorModel
import jp.pois.oxacillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns users who follows specific user.
 *
 * @param userId The ID of the user for whom to return results.
 * @param count The number of users to return per page, up to a maximum of 200.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friends] endpoint instance.
 * @return [CursorJsonApiAction] for [CursorUsers] model.
 * @see followingListByScreenName
 */
public fun <M: CursorModel<T>, T: Any> Friends.followingListByUserId(
    deserializer: DeserializationStrategy<M>,
    userId: Long,
    count: Int? = null,
    vararg options: Option
): CursorJsonApiAction<M, T> = followingList(deserializer, userId, null, count, *options)

public inline fun <reified M: CursorModel<T>, T: Any> Friends.followingListByUserId(
    userId: Long,
    count: Int? = null,
    vararg options: Option
): CursorJsonApiAction<M, T> = followingListByUserId(deserializer(), userId, count, *options)

/**
 * Returns users who follows specific user.
 *
 * @param screenName The screen name of the user for whom to return results.
 * @param count The number of users to return per page, up to a maximum of 200.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friends] endpoint instance.
 * @return [CursorJsonApiAction] for [CursorUsers] model.
 * @see followingListByUserId
 */
public fun <M: CursorModel<T>, T: Any> Friends.followingListByScreenName(
    deserializer: DeserializationStrategy<M>,
    screenName: String,
    count: Int? = null,
    vararg options: Option
): CursorJsonApiAction<M, T> = followingList(deserializer, null, screenName, count, *options)

public inline fun <reified M: CursorModel<T>, T: Any> Friends.followingListByScreenName(
    screenName: String,
    count: Int? = null,
    vararg options: Option
): CursorJsonApiAction<M, T> = followingListByScreenName(deserializer(), screenName, count, *options)

private fun <M: CursorModel<T>, T: Any> Friends.followingList(
    deserializer: DeserializationStrategy<M>,
    userId: Long? = null,
    screenName: String? = null,
    count: Int? = null,
    vararg options: Option
) = client.session.get("/1.1/friends/following/list.json") {
    emulationModes += EmulationMode.TwitterForiPhone

    parameters(
        "user_id" to userId,
        "screen_name" to screenName,
        "count" to count,
        *options
    )
}.cursorJson(deserializer)
