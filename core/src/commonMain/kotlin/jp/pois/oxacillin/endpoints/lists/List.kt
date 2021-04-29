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

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Lists
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns all lists the authenticating or specified user subscribes to, including their own. The user is specified using the user_id or screen_name parameters. If no user is given, the authenticating user is used.
 * A maximum of 100 results will be returned by this call. Subscribed lists are returned first, followed by owned lists. This means that if a user subscribes to 90 lists and owns 20 lists, this method returns 90 subscriptions and 10 owned lists. The reverse method returns owned lists first, so with reverse=true, 20 owned lists and 80 subscriptions would be returned. If your goal is to obtain every list a user owns or subscribes to, use [GET lists/ownerships](https://developer.twitter.com/rest/reference/get/lists/ownerships) and/or [GET lists/subscriptions](https://developer.twitter.com/rest/reference/get/lists/subscriptions) instead.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/get-lists-list)
 *
 * @param reverse Set this to true if you would like owned lists to be returned first. See description above for information on how this parameter works.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonArrayApiAction] for [TwitterList] model.
 */
public fun <T> Lists.list(
    deserializer: DeserializationStrategy<T>,
    reverse: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listInternal(deserializer, null, null, reverse, *options)

public inline fun <reified T> Lists.list(
    reverse: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = list(deserializer(), reverse, *options)

    /**
 * Returns all lists the authenticating or specified user subscribes to, including their own. The user is specified using the user_id or screen_name parameters. If no user is given, the authenticating user is used.
 * A maximum of 100 results will be returned by this call. Subscribed lists are returned first, followed by owned lists. This means that if a user subscribes to 90 lists and owns 20 lists, this method returns 90 subscriptions and 10 owned lists. The reverse method returns owned lists first, so with reverse=true, 20 owned lists and 80 subscriptions would be returned. If your goal is to obtain every list a user owns or subscribes to, use [GET lists/ownerships](https://developer.twitter.com/rest/reference/get/lists/ownerships) and/or [GET lists/subscriptions](https://developer.twitter.com/rest/reference/get/lists/subscriptions) instead.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/get-lists-list)
 *
 * @param userId The ID of the user for whom to return results. Helpful for disambiguating when a valid user ID is also a valid screen name. Note: : Specifies the ID of the user to get lists from. Helpful for disambiguating when a valid user ID is also a valid screen name.
 * @param reverse Set this to true if you would like owned lists to be returned first. See description above for information on how this parameter works.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonArrayApiAction] for [TwitterList] model.
 */
public fun <T> Lists.list(
    deserializer: DeserializationStrategy<T>,
    userId: Long,
    reverse: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listInternal(deserializer, userId, null, reverse, *options)

public inline fun <reified T> Lists.list(
    userId: Long,
    reverse: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = list(deserializer(), userId, reverse, *options)

    /**
 * Returns all lists the authenticating or specified user subscribes to, including their own. The user is specified using the user_id or screen_name parameters. If no user is given, the authenticating user is used.
 * A maximum of 100 results will be returned by this call. Subscribed lists are returned first, followed by owned lists. This means that if a user subscribes to 90 lists and owns 20 lists, this method returns 90 subscriptions and 10 owned lists. The reverse method returns owned lists first, so with reverse=true, 20 owned lists and 80 subscriptions would be returned. If your goal is to obtain every list a user owns or subscribes to, use [GET lists/ownerships](https://developer.twitter.com/rest/reference/get/lists/ownerships) and/or [GET lists/subscriptions](https://developer.twitter.com/rest/reference/get/lists/subscriptions) instead.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/get-lists-list)
 *
 * @param screenName The screen name of the user for whom to return results. Helpful for disambiguating when a valid screen name is also a user ID.
 * @param reverse Set this to true if you would like owned lists to be returned first. See description above for information on how this parameter works.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonArrayApiAction] for [TwitterList] model.
 */
public fun <T> Lists.list(
    deserializer: DeserializationStrategy<T>,
    screenName: String,
    reverse: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = listInternal(deserializer , null, screenName, reverse, *options)

public inline fun <reified T> Lists.list(
    screenName: String,
    reverse: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = list(deserializer(), screenName, reverse, *options)

private fun <T> Lists.listInternal(
    deserializer: DeserializationStrategy<T>,
    userId: Long? = null,
    screenName: String? = null,
    reverse: Boolean? = null,
    vararg options: Option
) = client.session.get("/1.1/lists/list.json") {
    parameters(
        "user_id" to userId,
        "screen_name" to screenName,
        "reverse" to reverse,
        *options
    )
}.json(deserializer)
