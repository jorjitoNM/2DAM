package com.example.examen2evajorgenovillo.ui.informes_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.examen2evajorgenovillo.R
import com.example.examen2evajorgenovillo.domain.model.Informe
import com.example.examen2evajorgenovillo.ui.alumnos_list.AlumnoItem
import com.example.examen2evajorgenovillo.ui.alumnos_list.AlumnosListEvents
import com.example.examen2evajorgenovillo.ui.common.UiEvent

@Composable
fun InformesListScreen (
    informesListViewModel: InformesListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    navigateToLogin : () -> Unit,
) {
    val uiState by informesListViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        informesListViewModel.handleEvent(InformesListEvents.GetAll)
    }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            } else if (it is UiEvent.NavigateToLogin) {
                navigateToLogin()
            }
            informesListViewModel.handleEvent(InformesListEvents.EventDone)
        }
    }

}

@Composable
fun InformesListContent(
    informes : List<Informe>
) {
    LazyColumn {
        this.items(items = informes, key = { informe -> informe.contenido }) { informe ->
            InformeItem(informe)
        }
    }
}

@Composable
fun InformeItem (
    informe : Informe
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
            text = stringResource(R.string.contenido) + " " + informe.contenido,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding4))
        )
    }
}