package weatherly.home.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.example.weatherly.R
import weatherly.coreUi.theme.WeatherlyTheme

@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.something_went_wrong),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@PreviewFontScale
@PreviewScreenSizes
@Preview
@Composable
private fun ErrorMessagePreview() {
    WeatherlyTheme {
        ErrorMessage()
    }
}
