package jp.nephy.penicillin.endpoint.parameter

enum class MediaType(val mimeType: String) {
    JPEG("image/jpeg"), PNG("image/png"), GIF("image/gif"), WebP("image/webp"), MP4("video/mp4")
}
