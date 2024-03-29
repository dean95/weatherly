package weatherly.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherly.R
import weatherly.home.ui.ForecastItemUiState
import weatherly.coreUi.theme.WeatherlyTheme
import weatherly.weather.ui.iconMap
import weatherly.coreUi.theme.spacing

@Composable
fun TodayForecastItem(
    forecastItemUiState: ForecastItemUiState,
    onForecastItemClick: (String, String) -> Unit,
    topPadding: Dp,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(top = topPadding)
            .clickable { onForecastItemClick(forecastItemUiState.id, forecastItemUiState.locationId) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = forecastItemUiState.date,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(all = MaterialTheme.spacing.medium)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = iconMap.getValue(forecastItemUiState.iconCode)),
                    contentDescription = forecastItemUiState.description,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(dimensionResource(id = R.dimen.forecast_icon_size_large))
                )

                Text(
                    text = forecastItemUiState.description,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(all = MaterialTheme.spacing.small)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = forecastItemUiState.maxTemp,
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = forecastItemUiState.minTemp,
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}

@PreviewFontScale
@PreviewScreenSizes
@Preview
@Composable
private fun TodayForecastItemPreview() {
    WeatherlyTheme {
        TodayForecastItem(
            forecastItemUiState = ForecastItemUiState(
                id = "2024-03-21T07:00:00+01:00",
                date = "November 30",
                minTemp = "13.2",
                maxTemp = "17.5",
                description = "Moderate Rain",
                iconCode = 18,
                locationId = "117910"
            ),
            onForecastItemClick = { _, _ -> /* no-op */ },
            topPadding = 32.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
