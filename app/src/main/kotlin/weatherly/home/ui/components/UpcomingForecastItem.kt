package weatherly.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import weatherly.home.ui.ForecastItemUiState
import weatherly.coreUi.theme.WeatherlyTheme
import weatherly.weather.ui.iconMap
import weatherly.coreUi.theme.spacing

@Composable
fun UpcomingForecastItem(
    forecastItemUiState: ForecastItemUiState,
    onForecastItemClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable {
                onForecastItemClick(
                    forecastItemUiState.id,
                    forecastItemUiState.locationId
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconMap.getValue(forecastItemUiState.iconCode)),
            contentDescription = forecastItemUiState.description,
            tint = Color.Unspecified
        )

        Column(
            modifier.weight(weight = 1.0f)
        ) {
            Text(
                text = forecastItemUiState.date,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = forecastItemUiState.description,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = forecastItemUiState.maxTemp,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )

        Text(
            text = forecastItemUiState.minTemp,
            style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray),
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )
    }
}

@PreviewFontScale
@PreviewScreenSizes
@Preview
@Composable
private fun UpcomingForecastItemPreview() {
    WeatherlyTheme {
        UpcomingForecastItem(
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
            modifier = Modifier.fillMaxWidth()
        )
    }
}
