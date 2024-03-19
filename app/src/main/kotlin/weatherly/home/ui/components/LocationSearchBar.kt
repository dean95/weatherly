package weatherly.home.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherly.R
import weatherly.core.viewState.Async
import weatherly.coreUi.LoadingIndicator
import weatherly.home.ui.LocationItemUiState
import weatherly.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSearchBar(
    locationItems: Async<List<LocationItemUiState>>,
    onQueryChange: (String) -> Unit,
    onLocationItemClick: (LocationItemUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = modifier,
        query = text,
        onQueryChange = {
            text = it
            onQueryChange(it)
        },
        onSearch = {
            active = false
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_for_city))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.cd_search_icon)
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {
                                if (text.isNotEmpty()) {
                                    text = ""
                                } else {
                                    active = false
                                }
                            }
                        ),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.cd_close_icon)
                )
            }
        }
    ) {
        when (locationItems) {
            is Async.Fail -> {
                Text(
                    text = stringResource(id = R.string.something_went_wrong),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.small
                        )
                )
            }

            Async.Loading -> {
                LoadingIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = MaterialTheme.spacing.medium)
                )
            }

            is Async.Success -> {
                if (locationItems.value.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.no_location),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = MaterialTheme.spacing.medium,
                                vertical = MaterialTheme.spacing.small
                            )
                    )
                } else {
                    locationItems.value.forEach { locationItemUiState ->
                        LocationItem(
                            locationItemUiState = locationItemUiState,
                            onLocationItemClick = {
                                onLocationItemClick(it)
                                active = false
                                text = it.name
                            },
                            modifier = Modifier
                                .padding(
                                    horizontal = MaterialTheme.spacing.medium,
                                    vertical = MaterialTheme.spacing.small
                                )
                                .fillMaxWidth()
                        )
                    }
                }
            }

            Async.Uninitialized -> {
                /* no-op */
            }
        }
    }
}

@Composable
private fun LocationItem(
    locationItemUiState: LocationItemUiState,
    onLocationItemClick: (LocationItemUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = locationItemUiState.name,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
            .clip(RoundedCornerShape(size = MaterialTheme.spacing.medium))
            .clickable {
                onLocationItemClick(locationItemUiState)
            }
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )
    )
}
