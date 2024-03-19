package weatherly.home.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import weatherly.home.ui.ForecastItemUiState
import weatherly.ui.theme.WeatherlyTheme
import weatherly.ui.theme.iconMap
import weatherly.ui.theme.spacing

@Composable
fun TodayForecastItem(
    forecastItemUiState: ForecastItemUiState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surfaceVariant),
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
                    tint = Color.Unspecified, modifier = Modifier.size(128.dp)
                )

                Text(
                    text = forecastItemUiState.description,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(all = MaterialTheme.spacing.small)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
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
                date = "November 30",
                minTemp = "13.2",
                maxTemp = "17.5",
                description = "Moderate Rain",
                iconCode = 18
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
