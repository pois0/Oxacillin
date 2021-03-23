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

package jp.pois.oxacillin.core.emulation

import jp.pois.oxacillin.core.auth.OAuthUtil
import io.ktor.http.*
import kotlin.random.Random

/**
 * Pre-defined profile for "Twitter for iPhone".
 */
internal class Twitter4iPhone: Emulation {
    override val headers: Headers = Headers.build {
        append("Accept-Language", "ja")
        append("Timezone", "Asia/Tokyo")
        append("User-Agent", "Twitter-iPhone/7.30 iOS/11.3.1 (Apple;iPhone10,1;;;;;1;2017)")
        append("X-B3-TraceId", b3TraceId)
        append("X-Client-UUID", clientUUID)
        append("X-Twitter-Active-User", "yes")
        append("X-Twitter-API-Version", "5")
        append("X-Twitter-Client", "Twitter-iPhone")
        append("X-Twitter-Client-DeviceID", "00000000-0000-0000-0000-000000000000")
        append("X-Twitter-Client-Language", "ja")
        append("X-Twitter-Client-Limit-Ad-Tracking", "1")
        append("X-Twitter-Client-Version", "7.30")
        append("X-Twitter-Polling", "true")
        append("X-Twitter-UTCOffset", "+0900")
    }

    companion object {
        private val clientUUID = OAuthUtil.randomUUID
        val b3TraceId: String
            get() = buildString {
                repeat(16) {
                    append(Random.nextInt(16).toString(16))
                }
            }
    }
}
