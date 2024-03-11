package weatherly.weather.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyForecastResponse(
    @SerialName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>,
    @SerialName("headline")
    val headline: Headline
) {

    @Serializable
    data class DailyForecast(
        @SerialName("Date")
        val date: String,
        @SerialName("day")
        val day: Day,
        @SerialName("EpochDate")
        val epochDate: Int,
        @SerialName("Link")
        val link: String,
        @SerialName("MobileLink")
        val mobileLink: String,
        @SerialName("Night")
        val night: Night,
        @SerialName("Sources")
        val sources: List<String>,
        @SerialName("Temperature")
        val temperature: Temperature
    ) {

        @Serializable
        data class Day(
            @SerialName("HasPrecipitation")
            val hasPrecipitation: Boolean,
            @SerialName("Icon")
            val icon: Int,
            @SerialName("IconPhrase")
            val iconPhrase: String,
            @SerialName("PrecipitationIntensity")
            val precipitationIntensity: String,
            @SerialName("PrecipitationType")
            val precipitationType: String
        )

        @Serializable
        data class Night(
            @SerialName("HasPrecipitation")
            val hasPrecipitation: Boolean,
            @SerialName("Icon")
            val icon: Int,
            @SerialName("IconPhrase")
            val iconPhrase: String,
            @SerialName("PrecipitationIntensity")
            val precipitationIntensity: String,
            @SerialName("PrecipitationType")
            val precipitationType: String
        )

        @Serializable
        data class Temperature(
            @SerialName("Maximum")
            val maximum: Maximum,
            @SerialName("Minimum")
            val minimum: Minimum
        ) {

            @Serializable
            data class Maximum(
                @SerialName("Unit")
                val unit: String,
                @SerialName("UnitType")
                val unitType: Int,
                @SerialName("Value")
                val value: Double
            )

            @Serializable
            data class Minimum(
                @SerialName("Unit")
                val unit: String,
                @SerialName("UnitType")
                val unitType: Int,
                @SerialName("Value")
                val value: Double
            )
        }
    }

    @Serializable
    data class Headline(
        @SerialName("Category")
        val category: String,
        @SerialName("EffectiveDate")
        val effectiveDate: String,
        @SerialName("EffectiveEpochDate")
        val effectiveEpochDate: Int,
        @SerialName("EndDate")
        val endDate: String,
        @SerialName("EndEpochDate")
        val endEpochDate: Int,
        @SerialName("Link")
        val link: String,
        @SerialName("MobileLink")
        val mobileLink: String,
        @SerialName("Severity")
        val severity: Int,
        @SerialName("Text")
        val text: String
    )
}
