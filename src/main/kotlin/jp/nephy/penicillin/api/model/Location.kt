package jp.nephy.penicillin.api.model

import com.github.salomonbrys.kotson.byInt
import com.github.salomonbrys.kotson.byString
import com.google.gson.JsonElement

class Location(val json: JsonElement) {
    val name by json.byString
    val woeid by json.byInt
}
