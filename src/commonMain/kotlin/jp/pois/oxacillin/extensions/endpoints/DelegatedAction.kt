package jp.pois.oxacillin.extensions.endpoints

import jp.pois.oxacillin.core.request.action.ApiAction
import jp.pois.oxacillin.core.request.action.DelegatedAction
import jp.pois.oxacillin.endpoints.Endpoint

/**
 * Creates an [ApiAction] that is resolved with passed lambda block.
 *
 * @param block Executed action block.
 *
 * @return New [ApiAction] with passed block.
 */
public fun <R> Endpoint.delegatedAction(block: suspend () -> R): ApiAction<R> {
    return DelegatedAction(client, block)
}