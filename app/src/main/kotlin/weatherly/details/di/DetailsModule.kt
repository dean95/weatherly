package weatherly.details.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import weatherly.details.ui.DetailsViewModel

val detailsModule = module {

    viewModel { (forecastId: String, locationId: String) ->
        DetailsViewModel(
            forecastId = forecastId,
            locationId = locationId,
            fetchFiveDayForecast = get()
        )
    }
}
