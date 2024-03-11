package weatherly.home.ui

import weatherly.weather.domain.model.Location

data class HomeUiState(
    val locationItems: List<LocationItemUiState> = listOf()
)

data class LocationItemUiState(
    val id: String,
    val name: String
)

fun Location.toLocationItemUiState() = LocationItemUiState(id, name)
