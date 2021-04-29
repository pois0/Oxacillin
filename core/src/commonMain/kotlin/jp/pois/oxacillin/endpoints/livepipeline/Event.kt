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

package jp.pois.oxacillin.endpoints.livepipeline


import jp.pois.oxacillin.core.emulation.EmulationMode
import jp.pois.oxacillin.core.request.ApiRequest
import jp.pois.oxacillin.core.request.action.StreamApiAction
import jp.pois.oxacillin.core.request.parameters
import jp.pois.oxacillin.core.session.ApiClient
import jp.pois.oxacillin.core.session.get
import jp.pois.oxacillin.core.streaming.handler.LivePipelineHandler
import jp.pois.oxacillin.core.streaming.listener.LivePipelineListener
import jp.pois.oxacillin.endpoints.LivePipeline
import jp.pois.oxacillin.endpoints.Option

/**
 * Undocumented endpoint.
 * 
 * @param ids Array of status id to track.
 * @param options Optional. Custom parameters of this request.
 * @receiver [LivePipeline] endpoint instance.
 * @return [StreamApiAction] for [LivePipelineHandler] handler with [LivePipelineListener] listener.
 */
public fun LivePipeline.event(
    ids: List<Long>,
    vararg options: Option
): StreamApiAction<LivePipelineListener, LivePipelineHandler> {
    val request = client.session.get("/1.1/live_pipeline/events") {
        emulationModes += EmulationMode.TwitterForiPhone

        parameters(
            "topic" to ids.joinToString(",") { "/tweet_engagement/$it" },
            *options
        )
    }

    return LivePipelineStreamApiAction(client, request)
}

private class LivePipelineStreamApiAction(
    client: ApiClient,
    request: ApiRequest,
): StreamApiAction<LivePipelineListener, LivePipelineHandler>(client, request) {
    override fun getHandler(listener: LivePipelineListener): LivePipelineHandler = LivePipelineHandler(client, listener)
}
