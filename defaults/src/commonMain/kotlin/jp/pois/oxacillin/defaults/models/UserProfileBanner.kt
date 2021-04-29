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

package jp.pois.oxacillin.defaults.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserProfileBanner(
    @SerialName("1080x360") public val resolution1080x360: Banner? = null,
    @SerialName("1500x500") public val resolution1500x500: Banner? = null,
    @SerialName("300x100") public val resolution300x100: Banner? = null,
    @SerialName("600x200") public val resolution600x200: Banner? = null,
    public val ipad: Banner? = null,
    @SerialName("ipad_retina") public val ipadRetina: Banner? = null,
    public val mobile: Banner? = null,
    @SerialName("mobile_retina") public val mobileRetina: Banner? = null,
    public val web: Banner? = null,
    @SerialName("web_retina") public val webRetina: Banner? = null
) {
    @Serializable
    public data class Banner(
        public val h: Int,
        public val w: Int,
        public val url: String
    )
}
