package weatherly.details.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import weatherly.details.ui.ForecastDetailsUiState
import weatherly.ui.theme.WeatherlyTheme
import weatherly.ui.theme.iconMap
import weatherly.ui.theme.spacing

@Composable
fun ForecastDetailsItem(
    forecastDetailsUiState: ForecastDetailsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = forecastDetailsUiState.date,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(all = MaterialTheme.spacing.medium)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.33f)
            ) {
                Icon(
                    painter = painterResource(id = iconMap.getValue(forecastDetailsUiState.iconCodeDay)),
                    contentDescription = forecastDetailsUiState.descriptionDay,
                    tint = Color.Unspecified, modifier = Modifier.size(128.dp)
                )

                Text(
                    text = forecastDetailsUiState.descriptionDay,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(all = MaterialTheme.spacing.small)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.33f)
            ) {
                Text(
                    text = forecastDetailsUiState.maxTemp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = forecastDetailsUiState.minTemp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.33f)
            ) {
                Icon(
                    painter = painterResource(id = iconMap.getValue(forecastDetailsUiState.iconCodeNight)),
                    contentDescription = forecastDetailsUiState.descriptionNight,
                    tint = Color.Unspecified, modifier = Modifier.size(128.dp)
                )

                Text(
                    text = forecastDetailsUiState.descriptionNight,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(all = MaterialTheme.spacing.small),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@PreviewFontScale
@PreviewScreenSizes
@Preview
@Composable
private fun ForecastDetailsItemPreview() {
    WeatherlyTheme {
        ForecastDetailsItem(
            forecastDetailsUiState = ForecastDetailsUiState(
                date = "November 30",
                iconCodeDay = 2,
                descriptionDay = "Mostly sunny",
                iconCodeNight = 36,
                descriptionNight = "Intermittent clouds",
                maxTemp = "25",
                minTemp = "15"
            )
        )
    }
}
