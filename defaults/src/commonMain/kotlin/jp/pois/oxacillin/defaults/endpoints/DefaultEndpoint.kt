package jp.pois.oxacillin.defaults.endpoints

import jp.pois.oxacillin.core.session.ApiClient

@DefaultEndpointDsl
public val ApiClient.Default: DefaultEndpoint
    get() = DefaultEndpoint(this)

public inline class DefaultEndpoint(override val client: ApiClient): Endpoint
