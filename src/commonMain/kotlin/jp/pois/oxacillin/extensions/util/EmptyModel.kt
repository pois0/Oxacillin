package jp.pois.oxacillin.extensions.util

import jp.pois.oxacillin.util.deserializer
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable

@Serializable
internal class EmptyModel

internal val emptyModelDeserializer: DeserializationStrategy<EmptyModel>
    get() = deserializer()