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

package jp.pois.oxacillin.extensions.models

import jp.pois.oxacillin.extensions.Card
import jp.pois.oxacillin.extensions.Status

/**
 * If true, this status has card entity.
 */
public val Status.hasCard: Boolean
    get() = cardUri != null

/**
 * A map of choices. Map size N must be 2 <= N <= 4.
 */
public val Card.choices: LinkedHashMap<String, Int>
    get() = linkedMapOf<String, Int>().also { 
        it.putIfNotNull(bindingValues.choice1Label?.value, bindingValues.choice1Count?.value?.toIntOrNull())
        it.putIfNotNull(bindingValues.choice2Label?.value, bindingValues.choice2Count?.value?.toIntOrNull())
        it.putIfNotNull(bindingValues.choice3Label?.value, bindingValues.choice3Count?.value?.toIntOrNull())
        it.putIfNotNull(bindingValues.choice4Label?.value, bindingValues.choice4Count?.value?.toIntOrNull())
    }

/**
 * If true, this card is final result.
 */
public val Card.isFinalResult: Boolean
    get() = bindingValues.countsAreFinal?.value == true

/**
 * Card end at date.
 */
public val Card.endAt: String?
    get() = bindingValues.endDatetimeUtc?.value

/**
 * Card last update at date.
 */
public val Card.lastUpdateAt: String?
    get() = bindingValues.lastUpdatedDatetimeUtc?.value

/**
 * Card duration in minutes.
 */
public val Card.minutes: Int?
    get() = bindingValues.durationMinutes?.value?.toIntOrNull()

private fun <K, V> MutableMap<K, V>.putIfNotNull(key: K?, value: V?) {
    if (key != null && value != null) {
        put(key, value)
    }
}
