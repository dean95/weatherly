package weatherly.weather.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import weatherly.weather.data.network.api.WeatherApi
import weatherly.weather.data.network.model.LocationSearchResultItem
import weatherly.weather.domain.model.Location

internal class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val backgroundDispatcher: CoroutineDispatcher
) : WeatherRepository {

    private val latestWeatherData = Mutex()

    override suspend fun fetchLocations(query: String): List<Location> =
        weatherApi
            .searchLocation(query)
            .map(LocationSearchResultItem::toDomain)
}
