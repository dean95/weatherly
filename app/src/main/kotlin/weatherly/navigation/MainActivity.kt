package weatherly.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import weatherly.details.ui.DetailsScreen
import weatherly.home.ui.HomeScreen
import weatherly.ui.theme.WeatherlyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherlyTheme {
                val navController = rememberNavController()
                MainScreen(navController = navController)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(viewModel = koinViewModel()) { forecastId, locationId ->
                navController.navigate(Screen.Details.createRoute(forecastId, locationId))
            }
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(FORECAST_ID_KEY) { type = NavType.StringType },
                navArgument(LOCATION_ID_KEY) { type = NavType.StringType }
            )
        ) {
            DetailsScreen(
                viewModel = koinViewModel(
                    parameters = {
                        parametersOf(
                            it.arguments?.getString(FORECAST_ID_KEY) ?: error("$FORECAST_ID_KEY not passed."),
                            it.arguments?.getString(LOCATION_ID_KEY) ?: error("$LOCATION_ID_KEY not passed.")
                        )
                    }
                ),
                onBackClick = navController::navigateUp)
        }
    }
}
