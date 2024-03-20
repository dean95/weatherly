package weatherly.details.ui

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import weatherly.core.viewState.Async
import weatherly.weather.domain.model.Forecast

data class DetailsUiState(
    val forecastItem: Async<ForecastDetailsUiState> = Async.Uninitialized
)

data class ForecastDetailsUiState(
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val descriptionDay: String,
    val descriptionNight: String,
    val iconCodeDay: Int,
    val iconCodeNight: Int
)

fun Forecast.toForecastDetailsUiState(): ForecastDetailsUiState {
    val localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("MMMM dd")

    return ForecastDetailsUiState(
        date = localDateTime.format(formatter),
        minTemp = "$minTemp°",
        maxTemp = "$maxTemp°",
        descriptionDay = descriptionDay,
        descriptionNight = descriptionNight,
        iconCodeDay = iconCodeDay,
        iconCodeNight = iconCodeNight
    )
}
