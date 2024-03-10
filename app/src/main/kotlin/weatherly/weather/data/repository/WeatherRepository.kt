package weatherly.weather.data.repository

import weatherly.weather.domain.model.Location

interface WeatherRepository {

    suspend fun fetchLocations(query: String): List<Location>
}
