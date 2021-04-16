package jp.pois.oxacillin.extensions.utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

internal val myJson = Json {
    ignoreUnknownKeys = true
}

@Suppress("unused")
@PublishedApi
internal inline fun <reified T> deserializer(): DeserializationStrategy<T> = Json.serializersModule.serializer()
