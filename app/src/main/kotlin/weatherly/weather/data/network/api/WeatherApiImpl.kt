package weatherly.weather.data.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import weatherly.core.api.BaseApi
import weatherly.weather.data.network.model.LocationSearchResultItem

private const val BASE_URL = "http://dataservice.accuweather.com"
private const val API_KEY = "tGU16bp3LdpJQHnatD23TQOKB90bs8di"

internal class WeatherApiImpl(
    client: HttpClient
) : WeatherApi, BaseApi(client) {

    override suspend fun searchLocation(query: String): List<LocationSearchResultItem> = execute {
        get("$BASE_URL/locations/v1/cities/autocomplete?apikey=$API_KEY&q=$query").body()
    }
}
