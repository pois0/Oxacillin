package jp.nephy.penicillin.api.model

import com.github.salomonbrys.kotson.byLong
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonElement

class Contributor(val json: JsonElement) {
    val id by json.byLong
    val idStr by json.byString("id_str")
    val screenName by json.byString("screen_name")
}
