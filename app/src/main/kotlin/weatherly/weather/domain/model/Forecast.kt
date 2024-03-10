package weatherly.weather.domain.model

data class Forecast(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val iconCode: Int
)
