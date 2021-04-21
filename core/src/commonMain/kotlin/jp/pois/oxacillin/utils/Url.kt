package jp.pois.oxacillin.utils

@OptIn(ExperimentalStdlibApi::class)
internal fun parseUrlQuery(query: String, capacity: Int) = buildMap<String, String>(capacity) {
    query.split("&").forEach { parameter ->
        parameter.split("=", limit = 2).let { (k, v) -> put(k, v) }
    }
}
