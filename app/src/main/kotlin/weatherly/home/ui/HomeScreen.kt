package weatherly.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import weatherly.core.viewState.Async
import weatherly.coreUi.LoadingIndicator
import weatherly.home.ui.components.ErrorMessage
import weatherly.home.ui.components.LocationSearchBar
import weatherly.home.ui.components.TodayForecastItem
import weatherly.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val homeUiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            LocationSearchBar(
                locationItems = homeUiState.locationItems,
                onQueryChange = viewModel::emitLocationSearchQuery,
                onLocationItemClick = {
                    viewModel.fetchForecast(it.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small)
            )
        }
    ) {
        when (val items = homeUiState.forecastItems) {
            is Async.Fail -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = MaterialTheme.spacing.medium),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorMessage()
                }
            }

            Async.Loading -> {
                LoadingIndicator(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is Async.Success -> {
                TodayForecastItem(
                    items.value.first(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = it.calculateTopPadding())
                )
            }

            Async.Uninitialized -> {
                /* no-op */
            }
        }
    }
}
