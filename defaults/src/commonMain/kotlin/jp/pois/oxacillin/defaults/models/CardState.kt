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
public data class CardState(
    public val card: Card
) {
    @Serializable
    public data class Card(
        public val name: String,
        public val url: String,
        @SerialName("card_type_url") public val cardTypeUrl: String,
        @SerialName("binding_values") public override val bindingValues: BindingValues,
    ): jp.pois.oxacillin.extensions.Card {
        @Serializable
        public data class BindingValues(
            @SerialName("choice1_label") public override val choice1Label: StringValue? = null,
            @SerialName("choice2_label") public override val choice2Label: StringValue? = null,
            @SerialName("choice3_label") public override val choice3Label: StringValue? = null,
            @SerialName("choice4_label") public override val choice4Label: StringValue? = null,

            @SerialName("choice1_count") public override val choice1Count: StringValue? = null,
            @SerialName("choice2_count") public override val choice2Count: StringValue? = null,
            @SerialName("choice3_count") public override val choice3Count: StringValue? = null,
            @SerialName("choice4_count") public override val choice4Count: StringValue? = null,

            @SerialName("selected_choice") public val selectedChoice: StringValue? = null,
            @SerialName("last_updated_datetime_utc") public override val lastUpdatedDatetimeUtc: StringValue? = null,
            @SerialName("end_datetime_utc") public override val endDatetimeUtc: StringValue? = null,
            @SerialName("counts_are_final") public override val countsAreFinal: BooleanValue? = null,
            @SerialName("duration_minutes") public override val durationMinutes: StringValue? = null,
            public val api: StringValue? = null,
            @SerialName("card_url") public val cardUrl: StringValue? = null
            ): jp.pois.oxacillin.extensions.Card.BindingValues {
            @Serializable
            public data class StringValue(
                public override val type: String,
                @SerialName("string_value") public override val value: String
            ): jp.pois.oxacillin.extensions.Card.BindingValues.StringValue

            @Serializable
            public data class BooleanValue(
                public override val type: String,
                @SerialName("boolean_value") public override val value: Boolean
            ): jp.pois.oxacillin.extensions.Card.BindingValues.BooleanValue
        }
    }
}
