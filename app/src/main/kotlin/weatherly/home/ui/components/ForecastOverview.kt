package weatherly.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import weatherly.core.viewState.Async
import weatherly.coreUi.LoadingIndicator
import weatherly.home.ui.ForecastItemUiState
import weatherly.coreUi.theme.spacing

@Composable
fun ForecastOverview(
    items: Async<List<ForecastItemUiState>>,
    paddingValues: PaddingValues,
    onForecastItemClick: (String, String) -> Unit
) {
    when (items) {
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
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                TodayForecastItem(
                    forecastItemUiState = items.value.first(),
                    onForecastItemClick = onForecastItemClick,
                    topPadding = paddingValues.calculateTopPadding(),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                items.value.drop(1).forEach {
                    UpcomingForecastItem(
                        forecastItemUiState = it,
                        onForecastItemClick = onForecastItemClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Async.Uninitialized -> {
            /* no-op */
        }
    }
}
