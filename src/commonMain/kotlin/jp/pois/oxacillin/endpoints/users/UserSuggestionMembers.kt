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

package jp.pois.oxacillin.endpoints.users

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Users
import jp.pois.oxacillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Access the users in a given category of the Twitter suggested user list and return their most recent status if they are not a protected user.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-suggestions-slug-members)
 * 
 * @param slug The short name of list or a category
 * @param options Optional. Custom parameters of this request.
 * @receiver [Users] endpoint instance.
 * @return [JsonArrayApiAction] for [User] model.
 */
public fun <T> Users.userSuggestionMembers(
    deserializer: DeserializationStrategy<T>,
    slug: String,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.get("/1.1/users/suggestions/$slug/members.json") {
    parameters(*options)
}.json(deserializer)

public inline fun <reified T> Users.userSuggestionMembers(
    slug: String,
    vararg options: Option
): JsonGeneralApiAction<T> = userSuggestionMembers(deserializer(), slug, *options)
