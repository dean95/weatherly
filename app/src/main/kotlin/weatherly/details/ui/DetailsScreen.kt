package weatherly.details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.weatherly.R
import weatherly.core.viewState.Async
import weatherly.details.ui.components.ForecastDetailsItem
import weatherly.coreUi.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit
) {
    val detailsUiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.details),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.back_button_size))
                            .clip(CircleShape)
                            .clickable { onBackClick() }
                            .padding(MaterialTheme.spacing.small),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.cd_go_back)
                    )
                },
                modifier = Modifier.shadow(
                    elevation = MaterialTheme.spacing.extraSmall
                )
            )
        }
    ) {
        when (val forecastDetailsUiState = detailsUiState.forecastItem) {
            is Async.Success -> {
                ForecastDetailsItem(
                    forecastDetailsUiState = forecastDetailsUiState.value,
                    modifier = Modifier.padding(top = it.calculateTopPadding())
                )
            }

            Async.Uninitialized, is Async.Fail, Async.Loading -> {
                /* no-op */
            }
        }
    }
}
