package weatherly.details.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import weatherly.weather.domain.useCase.FetchFiveDayForecast

class DetailsViewModel(
    private val forecastId: String,
    private val locationId: String,
    private val fetchFiveDayForecast: FetchFiveDayForecast
) : ViewModel() {

    init {
        Log.i("Dean", "DetailsViewModel: forecastId: $forecastId, locationId: $locationId")
    }
}
