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

package jp.pois.oxacillin.extensions

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Represents <a> tag in "source".
 */
@Serializable(with = jp.pois.oxacillin.extensions.Via.Serializer::class)
public class Via(
    /**
     * Source application url.
     */
    public val url: String,

    /**
     * Source application name.
     */
    public val name: String,

    /**
     * Source <a> tag attributes.
     */
    public val attributes: Map<String, String>
) {
    private companion object {
        val tagPattern = "^<a (.+?)>(.+?)</a>$".toRegex()
        val attributePattern = "^(.+?)=\"(.+?)\"$".toRegex()
    }

    public object Serializer: KSerializer<jp.pois.oxacillin.extensions.Via> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("via", PrimitiveKind.STRING)

        override fun deserialize(decoder: Decoder): jp.pois.oxacillin.extensions.Via {
            val matches = jp.pois.oxacillin.extensions.Via.Companion.tagPattern.matchEntire(decoder.decodeString())

            val tagAttributes = matches?.groupValues?.getOrNull(1)?.split(" ")?.map {
                val (k, v) = jp.pois.oxacillin.extensions.Via.Companion.attributePattern.matchEntire(it)!!.destructured
                k to v
            }?.toMap()
            val tagValue = matches?.groupValues?.getOrNull(2)
            val href = tagAttributes?.get("href")

            require(tagAttributes != null && tagValue != null && href != null) {
                "Invalid source html passed."
            }

            return jp.pois.oxacillin.extensions.Via(href, tagValue, tagAttributes)
        }

        override fun serialize(encoder: Encoder, value: jp.pois.oxacillin.extensions.Via) {
            val attributes = value.attributes.entries.joinToString(separator = " ") { (k, v) -> "$k=\"$v\"" }
            encoder.encodeString("<a $attributes>${value.name}</a>")
        }
    }
}
