package weatherly.weather.data.network.api

import weatherly.weather.data.network.model.DailyForecastResponse
import weatherly.weather.data.network.model.LocationSearchResultItem

interface WeatherApi {

    suspend fun searchLocation(query: String): List<LocationSearchResultItem>

    suspend fun fetchFiveDayForecast(locationKey: String, metric: Boolean = true): DailyForecastResponse
}
