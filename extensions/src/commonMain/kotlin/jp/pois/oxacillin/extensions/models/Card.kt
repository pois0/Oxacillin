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

package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.extensions.Card
import jp.pois.oxacillin.extensions.Status

/**
 * If true, this status has card entity.
 */
public val jp.pois.oxacillin.extensions.Status.hasCard: Boolean
    get() = cardUri != null

/**
 * A map of choices. Map size N must be 2 <= N <= 4.
 */
public val jp.pois.oxacillin.extensions.Card.choices: LinkedHashMap<String, Int>
    get() = linkedMapOf<String, Int>().also { 
        it.putIfNotNull(bindingValues.choice1Label?.stringValue, bindingValues.choice1Count?.stringValue?.toIntOrNull())
        it.putIfNotNull(bindingValues.choice2Label?.stringValue, bindingValues.choice2Count?.stringValue?.toIntOrNull())
        it.putIfNotNull(bindingValues.choice3Label?.stringValue, bindingValues.choice3Count?.stringValue?.toIntOrNull())
        it.putIfNotNull(bindingValues.choice4Label?.stringValue, bindingValues.choice4Count?.stringValue?.toIntOrNull())
    }

/**
 * If true, this card is final result.
 */
public val jp.pois.oxacillin.extensions.Card.isFinalResult: Boolean
    get() = bindingValues.countsAreFinal?.booleanValue == true

/**
 * Card end at date.
 */
public val jp.pois.oxacillin.extensions.Card.endAt: String?
    get() = bindingValues.endDatetimeUtc?.stringValue

/**
 * Card last update at date.
 */
public val jp.pois.oxacillin.extensions.Card.lastUpdateAt: String?
    get() = bindingValues.lastUpdatedDatetimeUtc?.stringValue

/**
 * Card duration in minutes.
 */
public val jp.pois.oxacillin.extensions.Card.minutes: Int?
    get() = bindingValues.durationMinutes?.stringValue?.toIntOrNull()

private fun <K, V> MutableMap<K, V>.putIfNotNull(key: K?, value: V?) {
    if (key != null && value != null) {
        put(key, value)
    }
}
