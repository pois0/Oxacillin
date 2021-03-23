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

@file:Suppress("UNUSED", "KDocMissingDocumentation")

package jp.pois.oxacillin.models

import blue.starry.jsonkt.JsonObject
import blue.starry.jsonkt.delegation.*
import jp.pois.oxacillin.core.session.ApiClient

public data class MomentGuide(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
    private val category by jsonObject
    public val categoryId: String by category.byString("category_id")
    public val categoryName: String by category.byString("name")
    public val categoryUri: String by category.byString("uri")
    public val impressionId: Long by long("impression_id")
    public val cursor: String by string("scroll_cursor")
    public val modules: List<Module> by modelList { Module(it, client) }
    public val trendModule: TrendModule? by nullableModel { TrendModule(it, client) }

    public data class Module(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        public val moduleType: String by string("module_type")
        public val moments: List<Moment> by modelList { Moment(it, client) }
    }

    public data class TrendModule(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        public val metadata: TrendMetadata by model { TrendMetadata(it, client) }
        public val trends: List<TrendType> by modelList { TrendType(it, client) }
    }
}
