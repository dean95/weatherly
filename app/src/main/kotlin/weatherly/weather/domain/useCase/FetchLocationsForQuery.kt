package weatherly.weather.domain.useCase

import weatherly.weather.data.repository.WeatherRepository
import weatherly.weather.domain.model.Location

class FetchLocationsForQuery(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(query: String): List<Location> = weatherRepository.fetchLocations(query)
}
