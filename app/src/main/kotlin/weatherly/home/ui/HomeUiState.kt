package weatherly.home.ui

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
    val id: String,
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val description: String,
    val iconCode: Int,
    val locationId: String
)

fun Location.toLocationItemUiState() = LocationItemUiState(id, name)

fun Forecast.toForecastItemUiState(locationId: String): ForecastItemUiState {
    val localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("MMMM dd")

    return ForecastItemUiState(
        id = date,
        date = localDateTime.format(formatter),
        minTemp = "$minTemp°",
        maxTemp = "$maxTemp°",
        description = descriptionDay,
        iconCode = iconCodeDay,
        locationId = locationId
    )
}
