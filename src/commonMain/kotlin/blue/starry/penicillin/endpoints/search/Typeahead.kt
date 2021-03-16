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

package blue.starry.penicillin.endpoints.search


import blue.starry.penicillin.core.emulation.EmulationMode
import blue.starry.penicillin.core.request.action.JsonGeneralApiAction
import blue.starry.penicillin.core.request.parameters
import blue.starry.penicillin.core.session.get
import blue.starry.penicillin.endpoints.Option
import blue.starry.penicillin.endpoints.Search
import blue.starry.penicillin.endpoints.common.TweetMode
import blue.starry.penicillin.models.SearchTypeahead
import blue.starry.penicillin.models.SearchUniversal

/**
 * Undocumented endpoint.
 *
 * @param options Optional. Custom parameters of this request.
 * @receiver [Search] endpoint instance.
 * @return [JsonGeneralApiAction] for [SearchUniversal] model.
 */
public fun Search.typeahead(
    query: String,
    tweetMode: TweetMode = TweetMode.Default,
    vararg options: Option
): JsonGeneralApiAction<SearchTypeahead> = client.session.get("/1.1/search/typeahead.json") {
    emulationModes += EmulationMode.TwitterForiPhone

    parameters(
        "cards_platform" to "iPhone-13",
        "contributor_details" to "1",
        "count" to "1200",
        "ext" to "altText,info360,mediaColor,mediaRestrictions,mediaStats,stickerInfo",
        "include_cards" to "1",
        "include_carousels" to "1",
        "include_entities" to "1",
        "include_ext_media_color" to "true",
        "include_media_features" to "true",
        "include_my_retweet" to "1",
        "include_profile_interstitial_type" to "true",
        "include_profile_location" to "true",
        "include_reply_count" to "1",
        "include_user_entities" to "true",
        "include_user_hashtag_entities" to "true",
        "include_user_mention_entities" to "true",
        "include_user_symbol_entities" to "true",
        "media_tagging_in_prefetch" to "true",
        "prefetch" to "true",
        "result_type" to "all",
        "src" to "search_box",
        "users_cache_age" to "146522",
        "q" to query,
        "tweet_mode" to tweetMode,
        *options
    )
}.jsonObject { SearchTypeahead(it, client) }
