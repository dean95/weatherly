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

    private val latestForecastForLocation: MutableMap<String, List<Forecast>> = mutableMapOf()

    override suspend fun fetchLocations(query: String): List<Location> =
        weatherApi
            .searchLocation(query)
            .map(LocationSearchResultItem::toDomain)

    override suspend fun fetchFiveDayForecast(locationKey: String, refresh: Boolean): List<Forecast> {
        return if (refresh || locationKey !in latestForecastForLocation) {
            applicationScope.async {
                val networkResponse = weatherApi.fetchFiveDayForecast(locationKey)
                val forecast = networkResponse.toForecast()
                latestWeatherDataMutex.withLock {
                    latestForecastForLocation[locationKey] = forecast
                }
                forecast
            }.await()
        } else {
            latestWeatherDataMutex.withLock { latestForecastForLocation.getValue(locationKey) }
        }
    }
}
