package weatherly.core.api

import io.ktor.client.HttpClient

abstract class BaseApi(private val client: HttpClient) {

    suspend fun <T> execute(block: suspend HttpClient.() -> T): T = client.block()
}
