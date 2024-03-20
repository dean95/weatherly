package weatherly.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import weatherly.core.viewState.Async
import weatherly.weather.domain.model.Location
import weatherly.weather.domain.useCase.FetchFiveDayForecast
import weatherly.weather.domain.useCase.FetchLocationsForQuery

private const val INITIAL_LOCATION_SEARCH_QUERY = ""
private const val SEARCH_DELAY_MILLIS = 300L
private const val MIN_QUERY_LENGTH = 1

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val fetchLocationsForQuery: FetchLocationsForQuery,
    private val fetchFiveDayForecast: FetchFiveDayForecast
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val locationSearchQueryState = MutableStateFlow(INITIAL_LOCATION_SEARCH_QUERY)

    init {
        observeLocationsSearchResult()
    }

    fun emitLocationSearchQuery(query: String) {
        locationSearchQueryState.tryEmit(query)
    }

    fun fetchForecast(locationKey: String) {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(forecastItems = Async.Loading) }
                val forecast = fetchFiveDayForecast(locationKey)
                _uiState.update {
                    it.copy(
                        forecastItems = Async.Success(
                            forecast.map { forecast ->
                                forecast.toForecastItemUiState(locationKey)
                            }
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(forecastItems = Async.Fail(e)) }
            }
        }
    }

    private fun observeLocationsSearchResult() {
        viewModelScope.launch {
            locationSearchQueryState
                .debounce(SEARCH_DELAY_MILLIS)
                .filter(String::isNotBlank)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    if (query.length >= MIN_QUERY_LENGTH) {
                        try {
                            _uiState.update { it.copy(locationItems = Async.Loading) }
                            val locations = fetchLocationsForQuery(query)
                            flowOf(locations)
                        } catch (e: Exception) {
                            _uiState.update { it.copy(locationItems = Async.Fail(e)) }
                            emptyFlow()
                        }
                    } else {
                        emptyFlow()
                    }
                }
                .collect { locations ->
                    _uiState.update {
                        it.copy(locationItems = Async.Success(locations.map(Location::toLocationItemUiState)))
                    }
                }
        }
    }
}
