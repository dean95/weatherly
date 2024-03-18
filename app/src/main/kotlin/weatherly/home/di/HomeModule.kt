package weatherly.home.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import weatherly.home.ui.HomeViewModel

val homeModule = module {

    viewModel {
        HomeViewModel(
            fetchLocationsForQuery = get(),
            fetchFiveDayForecast = get()
        )
    }
}
