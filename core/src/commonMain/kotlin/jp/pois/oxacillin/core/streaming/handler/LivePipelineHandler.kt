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

package jp.pois.oxacillin.core.streaming.handler

import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.core.streaming.listener.LivePipelineListener
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * Default LivePipeline [StreamHandler].
 * Accepts listener of [LivePipelineListener].
 */
public class LivePipelineHandler(override val client: ApiClient, override val listener: LivePipelineListener): StreamHandler<LivePipelineListener> {
    override suspend fun handle(json: JsonObject) {
        val pipeline = Json.decodeFromJsonElement<LivePipeline>(json)
        val topic = pipeline.topic
        val id = topic.split("/").lastOrNull()?.toLongOrNull() ?: return listener.onUnhandledJson(json)

        if (pipeline.topic.startsWith("/tweet_engagement/")) {
            when {
                pipeline.payload.tweetEngagement.likeCount != null -> {
                    listener.onUpdateLikeCount(id, pipeline.payload.tweetEngagement.likeCount)
                }
                pipeline.payload.tweetEngagement.retweetCount != null -> {
                    listener.onUpdateRetweetCount(id, pipeline.payload.tweetEngagement.retweetCount)
                }
                pipeline.payload.tweetEngagement.replyCount != null -> {
                    listener.onUpdateReplyCount(id, pipeline.payload.tweetEngagement.replyCount)
                }
                else -> {
                    listener.onUnhandledJson(json)
                }
            }
        } else {
            listener.onUnhandledJson(json)
        }

        listener.onAnyJson(json)
    }

    @Serializable
    private class LivePipeline (
        val topic: String,
        val payload: Payload
    ) {
        @Serializable
        class Payload(val tweetEngagement: TweetEngagement) {
            @Serializable
            class TweetEngagement(
                val likeCount: Int?,
                val retweetCount: Int?,
                val replyCount: Int?
            )
        }
    }
}
