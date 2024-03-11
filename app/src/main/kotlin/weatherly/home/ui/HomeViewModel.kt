package weatherly.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.IOException
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
import weatherly.weather.domain.model.Location
import weatherly.weather.domain.useCase.FetchLocationsForQuery

private const val INITIAL_LOCATION_SEARCH_QUERY = ""
private const val SEARCH_DELAY_MILLIS = 300L
private const val MIN_QUERY_LENGTH = 1

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeViewModel(
    private val fetchLocationsForQuery: FetchLocationsForQuery
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

    private fun observeLocationsSearchResult() {
        viewModelScope.launch {
            locationSearchQueryState
                .debounce(SEARCH_DELAY_MILLIS)
                .filter(String::isNotBlank)
                .distinctUntilChanged()
                .flatMapLatest {
                    if (it.length >= MIN_QUERY_LENGTH) {
                        try {
                            // TODO: Start loading indicator here
                            val locations = fetchLocationsForQuery(it)
                            // TODO: End loading indicator here
                            flowOf(locations)
                        } catch (ioe: IOException) {
                            // TODO: Update UI state with error here
                            emptyFlow()
                        }
                    } else {
                        emptyFlow()
                    }
                }
                .collect { locations ->
                    _uiState.update {
                        it.copy(locationItems = locations.map(Location::toLocationItemUiState))
                    }
                }
        }
    }
}
