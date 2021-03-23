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

package jp.pois.oxacillin.endpoints.trends

import jp.pois.oxacillin.core.request.action.JsonGeneralApiAction
import jp.pois.oxacillin.core.request.json
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.Trends
import jp.pois.oxacillin.utils.deserializer
import kotlinx.serialization.DeserializationStrategy

/**
 * Returns the locations that Twitter has trending topic information for, closest to a specified location.
 * The response is an array of "locations" that encode the location's WOEID and some other human-readable information such as a canonical name and country the location belongs in.
 * A WOEID is a [Yahoo! Where On Earth ID](http://developer.yahoo.com/geo/geoplanet/).
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/trends/locations-with-trending-topics/api-reference/get-trends-closest)
 * 
 * @param latitude If provided with a longitude parameter the available trend locations will be sorted by distance, nearest to furthest, to the co-ordinate pair. The valid ranges for longitude is -180.0 to +180.0 (West is negative, East is positive) inclusive.
 * @param longitude If provided with a latitude parameter the available trend locations will be sorted by distance, nearest to furthest, to the co-ordinate pair. The valid ranges for longitude is -180.0 to +180.0 (West is negative, East is positive) inclusive.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Trends] endpoint instance.
 * @return [JsonArrayApiAction] for [TrendArea] model.
 */
public fun <T> Trends.closestAreas(
    deserializer: DeserializationStrategy<T>,
    latitude: Double,
    longitude: Double,
    vararg options: Option
): JsonGeneralApiAction<T> = client.session.get("/1.1/trends/closest.json") {
    parameters(
        "lat" to latitude,
        "long" to longitude,
        *options
    )
}.json(deserializer)

public inline fun <reified T> Trends.closestAreas(
    latitude: Double,
    longitude: Double,
    vararg options: Option
): JsonGeneralApiAction<T> = closestAreas(deserializer(), latitude, longitude, *options)
