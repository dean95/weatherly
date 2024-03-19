package weatherly.home.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import weatherly.home.ui.components.LocationSearchBar
import weatherly.ui.theme.spacing

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val homeUiState = viewModel.uiState.collectAsState().value

    Scaffold {
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
}
