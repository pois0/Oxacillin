@file:Suppress("NOTHING_TO_INLINE")

package jp.pois.oxacillin.utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer

internal inline fun <reified T: JsonElement> JsonElement.castOrNull() = this as? T

internal inline fun JsonElement.getOrNull(index: Int) = castOrNull<JsonArray>()?.get(index)

internal inline fun JsonElement.getOrNull(key: String) = castOrNull<JsonObject>()?.get(key)

@Suppress("unused")
@PublishedApi
internal inline fun <reified T> deserializer(): DeserializationStrategy<T> = Json.serializersModule.serializer()

@PublishedApi
internal inline fun JsonObjectBuilder.putAll(vararg pairs: Pair<String, Any?>) {
    for ((key, value) in pairs) {
        when (value) {
            is JsonElement -> put(key, value)
            else -> put(key, Json.encodeToJsonElement(value))
        }
    }
}
