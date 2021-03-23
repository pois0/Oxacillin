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

package jp.pois.oxacillin.endpoints.account

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.append
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.multiPartBody
import jp.pois.oxacillin.core.session.post
import jp.pois.oxacillin.endpoints.Account
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.media.MediaType
import jp.pois.oxacillin.util.deserializer
import io.ktor.client.request.forms.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.DeserializationStrategy

/**
 * Updates the authenticating user's profile image. Note that this method expects raw multipart data, not a URL to an image.
 *
 * This method asynchronously processes the uploaded file before updating the user's profile image URL. You can either update your local cache the next time you request the user's information, or, at least 5 seconds after uploading the image, ask for the updated URL using [GET users/show](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-users-show).
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/manage-account-settings/api-reference/post-account-update_profile_image)
 *
 * @param file Required. The avatar image for the profile. Must be a valid GIF, JPG, or PNG image of less than 700 kilobytes in size. Images with width larger than 400 pixels will be scaled down. Animated GIFs will be converted to a static GIF of the first frame, removing the animation.
 * @param mediaType Required. The type of file.
 * @param includeEntities Optional. The entities node will not be included when set to false.
 * @param skipStatus Optional. When set to either true, t or 1 statuses will not be included in the returned user object.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Account] endpoint instance.
 * @return [JsonGeneralApiAction] for [VerifyCredentials] model.
 */
public fun <T> Account.updateProfileImage(
    deserializer: DeserializationStrategy<T>,
    file: ByteArray,
    mediaType: MediaType,
    includeEntities: Boolean? = null,
    skipStatus: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.post("/1.1/account/update_profile_image.json") {
    multiPartBody {
        append("image", "blob", mediaType.contentType) {
            writeFully(file)
        }
        append(
            "include_entities" to includeEntities,
            "skip_status" to skipStatus,
            *options
        )
    }
}.json(deserializer)

public inline fun <reified T> Account.updateProfileImage(
    file: ByteArray,
    mediaType: MediaType,
    includeEntities: Boolean? = null,
    skipStatus: Boolean? = null,
    vararg options: Option
): JsonGeneralApiAction<T> = updateProfileImage(
    deserializer(), file, mediaType, includeEntities, skipStatus, *options
)
