package jp.nephy.penicillin.models

import com.google.gson.JsonObject
import jp.nephy.jsonkt.byInt
import jp.nephy.jsonkt.byModel
import jp.nephy.jsonkt.byString
import jp.nephy.jsonkt.byStringList


data class Configuration(override val json: JsonObject): PenicillinModel {
    val charactersReservedPerMedia by json.byInt("characters_reserved_per_media")
    val clientEventUrl by json.byString("client_event_url")
    val dmTextCharacterLimit by json.byInt("dm_text_character_limit")
    val maxMediaPerUpload by json.byInt("max_media_per_upload")
    val nonUsernamePaths by json.byStringList("non_username_paths")
    val photoSizeLimit by json.byInt("photo_size_limit")
    val photoSizes by json.byModel<Photo>(key = "photo_sizes")
    val shortUrlLength by json.byInt("short_url_length")
    val shortUrlLengthHttps by json.byInt("short_url_length_https")
}