package weatherly.navigation

const val HOME_SCREEN_ROUTE = "Home"
const val FORECAST_DETAILS_ROUTE = "ForecastDetails"
const val LOCATION_ID_KEY = "locationId"
const val FORECAST_ID_KEY = "forecastId"
const val FORECAST_DETAILS_ROUTE_WITH_PARAMS = "$FORECAST_DETAILS_ROUTE/{$FORECAST_ID_KEY}/{$LOCATION_ID_KEY}"

sealed class Screen(val route: String) {
    data object Home : Screen(HOME_SCREEN_ROUTE)
    data object Details : Screen(FORECAST_DETAILS_ROUTE_WITH_PARAMS) {
        fun createRoute(
            forecastId: String,
            locationId: String
        ) = "$FORECAST_DETAILS_ROUTE/$forecastId/$locationId"
    }
}
