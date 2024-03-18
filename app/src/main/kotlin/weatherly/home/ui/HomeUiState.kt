package weatherly.home.ui

import weatherly.core.viewState.Async
import weatherly.weather.domain.model.Location

data class HomeUiState(
    val locationItems: Async<List<LocationItemUiState>> = Async.Uninitialized
)

data class LocationItemUiState(
    val id: String,
    val name: String
)

fun Location.toLocationItemUiState() = LocationItemUiState(id, name)
