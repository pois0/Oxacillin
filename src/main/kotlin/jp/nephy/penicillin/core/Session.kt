@file:Suppress("UNUSED")

package jp.nephy.penicillin.core

import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import jp.nephy.penicillin.core.auth.Credentials
import jp.nephy.penicillin.core.i18n.LocalizedString
import jp.nephy.penicillin.endpoints.EndpointHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import mu.KLogger
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

data class Session(private val underlyingHttpClient: HttpClient, override val coroutineContext: CoroutineContext, val logger: KLogger, val credentials: Credentials, val option: ClientOption): Closeable, CoroutineScope {
    val httpClient: HttpClient
        get() = if (underlyingHttpClient.coroutineContext.isActive) {
            underlyingHttpClient
        } else {
            throw PenicillinLocalizedException(LocalizedString.SessionAlreadyClosed)
        }

    fun call(
        method: HttpMethod, path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}
    ): PenicillinRequest {
        return PenicillinRequestBuilder(this, method, protocol, host, path).apply(builder).build()
    }

    fun get(path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}): PenicillinRequest {
        return call(HttpMethod.Get, path, host, protocol, builder)
    }

    fun post(path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}): PenicillinRequest {
        return call(HttpMethod.Post, path, host, protocol, builder)
    }

    fun put(path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}): PenicillinRequest {
        return call(HttpMethod.Put, path, host, protocol, builder)
    }

    fun patch(path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}): PenicillinRequest {
        return call(HttpMethod.Patch, path, host, protocol, builder)
    }

    fun delete(path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}): PenicillinRequest {
        return call(HttpMethod.Delete, path, host, protocol, builder)
    }

    fun head(path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}): PenicillinRequest {
        return call(HttpMethod.Head, path, host, protocol, builder)
    }

    fun options(path: String, host: EndpointHost = EndpointHost.Default, protocol: URLProtocol = URLProtocol.HTTPS, builder: PenicillinRequestBuilder.() -> Unit = {}): PenicillinRequest {
        return call(HttpMethod.Options, path, host, protocol, builder)
    }

    override fun close() {
        underlyingHttpClient.close()
    }
}
