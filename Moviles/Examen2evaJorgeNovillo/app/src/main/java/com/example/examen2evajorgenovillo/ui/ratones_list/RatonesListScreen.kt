package com.example.examen2evajorgenovillo.ui.ratones_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.examen2evajorgenovillo.R
import com.example.examen2evajorgenovillo.domain.model.Raton
import com.example.examen2evajorgenovillo.ui.common.UiEvent

@Composable
fun RatonesListScreen(
    ratonesListViewModel: RatonesListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigateToLogin : () -> Unit,
) {
    val uiState by ratonesListViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        ratonesListViewModel.handleEvent(RatonesListEvents.GetAll)
    }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }  else if (it is UiEvent.NavigateToLogin) {
                navigateToLogin()
            }
            ratonesListViewModel.handleEvent(RatonesListEvents.EventDone)
        }
    }
    RatonesListScreenContent(
        ratones = uiState.ratones,
        isLoading = uiState.isLoading
    )
}

@Composable
fun RatonesListScreenContent(
    ratones: List<Raton> = emptyList(),
    isLoading: Boolean = false,
) {
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.size65))
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                trackColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    } else {
        LazyColumn {
            this.items(items = ratones, key = { raton -> raton.nombre }) { raton ->
                RatonItem(raton)
            }
        }
    }
}

@Composable
fun RatonItem(
    raton: Raton = Raton()
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(
            dimensionResource(R.dimen.border1dp),
            MaterialTheme.colorScheme.onSecondaryContainer
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimensionResource(R.dimen.padding8))
    ) {
        Text(
            text = stringResource(R.string.name) + " " + raton.nombre,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding4))
        )

    }
}