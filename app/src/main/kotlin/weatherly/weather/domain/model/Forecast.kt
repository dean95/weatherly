package weatherly.weather.domain.model

data class Forecast(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val descriptionDay: String,
    val iconCodeDay: Int,
    val descriptionNight: String,
    val iconCodeNight: Int
)
