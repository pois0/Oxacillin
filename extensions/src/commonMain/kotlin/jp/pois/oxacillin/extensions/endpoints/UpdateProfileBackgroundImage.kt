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

@file:Suppress("UNUSED", "Deprecation", "KotlinDeprecation")

package jp.pois.oxacillin.extensions.endpoints

import jp.pois.oxacillin.core.request.action.ApiAction
import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.endpoints.Account
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.account.updateProfileBackgroundImage
import jp.pois.oxacillin.endpoints.media
import jp.pois.oxacillin.endpoints.media.MediaComponent
import jp.pois.oxacillin.extensions.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Update profile background image.
 *
 * @param media Uploading media.
 * @param tile Optional. Whether or not to tile the background image. If set to true , t or 1 the background image will be displayed tiled. The image will not be tiled otherwise.
 * @param includeEntities Optional. The entities node will not be included when set to false.
 * @param skipStatus Optional. When set to either true, t or 1 statuses will not be included in the returned user object.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Account] endpoint instance.
 * @return [JsonGeneralApiAction] for [T] model.
 */
public fun <T> Account.updateProfileBackgroundImage(
    deserializer: DeserializationStrategy<T>,
    media: MediaComponent,
    tile: Boolean? = null,
    includeEntities: Boolean? = null,
    skipStatus: Boolean? = null,
    vararg options: Option
): ApiAction<JsonGeneralApiAction<T>> = delegatedAction {
    val result = client.media.uploadMedia<jp.pois.oxacillin.extensions.InternalMedia>(media).execute()
    updateProfileBackgroundImage(deserializer, result.mediaId, tile, includeEntities, skipStatus, *options)
}

public inline fun <reified T> Account.updateProfileBackgroundImage(
    media: MediaComponent,
    tile: Boolean? = null,
    includeEntities: Boolean? = null,
    skipStatus: Boolean? = null,
    vararg options: Option
): ApiAction<JsonGeneralApiAction<T>> = updateProfileBackgroundImage(deserializer(), media, tile, includeEntities, skipStatus, *options)
