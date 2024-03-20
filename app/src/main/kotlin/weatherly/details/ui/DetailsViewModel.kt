package weatherly.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import weatherly.core.viewState.Async
import weatherly.weather.domain.useCase.FetchFiveDayForecast

class DetailsViewModel(
    private val forecastId: String,
    private val locationId: String,
    private val fetchFiveDayForecast: FetchFiveDayForecast
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchForecast()
    }

    private fun fetchForecast() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(forecastItem = Async.Loading) }
                val forecast = fetchFiveDayForecast(locationId).find {
                    it.date == forecastId
                } ?: error("Forecast not found")

                _uiState.update {
                    it.copy(
                        forecastItem = Async.Success(forecast.toForecastDetailsUiState())
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(forecastItem = Async.Fail(e)) }
            }
        }
    }
}
