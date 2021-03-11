@file:Suppress("NOTHING_TO_INLINE")

package blue.starry.penicillin.core.util

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

internal inline fun <reified T: JsonElement> JsonElement.castOrNull() = this as? T

internal inline fun JsonElement.getOrNull(index: Int) = castOrNull<JsonArray>()?.get(index)

internal inline fun JsonElement.getOrNull(key: String) = castOrNull<JsonObject>()?.get(key)