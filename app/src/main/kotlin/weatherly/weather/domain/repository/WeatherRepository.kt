package weatherly.weather.domain.repository

import weatherly.weather.domain.model.Forecast
import weatherly.weather.domain.model.Location

interface WeatherRepository {

    suspend fun fetchLocations(query: String): List<Location>

    suspend fun fetchFiveDayForecast(locationKey: String, refresh: Boolean): List<Forecast>
}
