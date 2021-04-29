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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.pois.oxacillin.defaults.endpoints.media

import jp.pois.oxacillin.core.request.action.EmptyApiAction
import jp.pois.oxacillin.defaults.endpoints.Media
import jp.pois.oxacillin.endpoints.Option
import jp.pois.oxacillin.endpoints.media
import jp.pois.oxacillin.endpoints.media.MediaComponent
import jp.pois.oxacillin.endpoints.media.uploadAppend

/**
 * The APPEND command is used to upload a chunk (consecutive byte range) of the media file. For example, a 3 MB file could be split into 3 chunks of size 1 MB, and uploaded using 3 APPEND command requests. After the entire file is uploaded, the next step is to call the FINALIZE command.
 * There are a number of advantages of uploading a media file in small chunks:
 * - Improved reliability and success rates under low bandwidth network conditions
 * - Uploads can be paused and resumed
 * - File chunks can be retried individually
 * - Ability to tune chunk sizes to match changing network conditions e.g on cellular clients
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/media/upload-media/api-reference/post-media-upload-append)
 * 
 * @param mediaId The media_id returned from the INIT command.
 * @param segmentIndex An ordered index of file chunk. It must be between 0-999 inclusive. The first segment has index 0, second segment has index 1, and so on.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Media] endpoint instance.
 * @return [EmptyApiAction].
 */
public inline fun Media.uploadAppend(
    media: MediaComponent,
    mediaId: Long,
    segmentIndex: Int,
    mediaKey: String? = null,
    vararg options: Option
): EmptyApiAction = client.media.uploadAppend(media, mediaId, segmentIndex, mediaKey, *options)
