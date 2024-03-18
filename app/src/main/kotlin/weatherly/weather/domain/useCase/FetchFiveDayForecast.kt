package weatherly.weather.domain.useCase

import weatherly.weather.domain.model.Forecast
import weatherly.weather.domain.repository.WeatherRepository

class FetchFiveDayForecast(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(
        locationKey: String,
        refresh: Boolean = false
    ): List<Forecast> =
        weatherRepository.fetchFiveDayForecast(locationKey, refresh)
}
