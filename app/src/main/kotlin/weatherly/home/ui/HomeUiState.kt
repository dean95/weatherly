package weatherly.home.ui

import weatherly.core.viewState.Async
import weatherly.weather.domain.model.Forecast
import weatherly.weather.domain.model.Location

data class HomeUiState(
    val locationItems: Async<List<LocationItemUiState>> = Async.Uninitialized,
    val forecastItems: Async<List<ForecastItemUiState>> = Async.Uninitialized
)

data class LocationItemUiState(
    val id: String,
    val name: String
)

data class ForecastItemUiState(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val iconCode: Int
)

fun Location.toLocationItemUiState() = LocationItemUiState(id, name)

fun Forecast.toForecastItemUiState() =
    ForecastItemUiState(
        date,
        minTemp,
        maxTemp,
        description,
        iconCode
    )
