package jp.pois.oxacillin.extensions.utils

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable

@Serializable
internal class EmptyModel

internal val emptyModelDeserializer: DeserializationStrategy<EmptyModel>
    get() = deserializer()