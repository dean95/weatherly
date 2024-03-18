package weatherly.weather.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import weatherly.weather.data.network.api.WeatherApi
import weatherly.weather.data.network.model.LocationSearchResultItem
import weatherly.weather.domain.model.Forecast
import weatherly.weather.domain.model.Location
import weatherly.weather.domain.repository.WeatherRepository

internal class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val applicationScope: CoroutineScope
) : WeatherRepository {

    private val latestWeatherDataMutex = Mutex()

    private var latestForecast: List<Forecast> = emptyList()

    override suspend fun fetchLocations(query: String): List<Location> =
        weatherApi
            .searchLocation(query)
            .map(LocationSearchResultItem::toDomain)

    override suspend fun fetchFiveDayForecast(locationKey: String, refresh: Boolean): List<Forecast> {
        return if (refresh || latestForecast.isEmpty()) {
            applicationScope.async {
                val networkResponse = weatherApi.fetchFiveDayForecast(locationKey)
                val forecast = networkResponse.toForecast()
                latestWeatherDataMutex.withLock {
                    latestForecast = forecast
                }
                forecast
            }.await()
        } else {
            latestWeatherDataMutex.withLock { this.latestForecast }
        }
    }
}
