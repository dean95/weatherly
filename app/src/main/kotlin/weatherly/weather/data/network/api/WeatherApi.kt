package weatherly.weather.data.network.api

import weatherly.weather.data.network.model.LocationSearchResultItem

interface WeatherApi {

    suspend fun searchLocation(query: String): List<LocationSearchResultItem>
}
