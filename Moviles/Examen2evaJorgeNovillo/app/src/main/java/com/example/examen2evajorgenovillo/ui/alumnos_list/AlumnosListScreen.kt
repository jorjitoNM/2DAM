package com.example.examen2evajorgenovillo.ui.alumnos_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.examen2evajorgenovillo.R
import com.example.examen2evajorgenovillo.domain.model.Alumno
import com.example.examen2evajorgenovillo.domain.model.Asignatura
import com.example.examen2evajorgenovillo.ui.common.UiEvent

@Composable
fun AlumnosListScreen(
    alumnosListViewModel: AlumnosListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {},
    navigateToLogin : () -> Unit,
) {
    val uiState by alumnosListViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        alumnosListViewModel.handleEvent(AlumnosListEvents.GetAll)
    }

    LaunchedEffect(uiState.event) {
        uiState.event?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            } else if (it is UiEvent.NavigateToLogin) {
                navigateToLogin()
            }
            alumnosListViewModel.handleEvent(AlumnosListEvents.EventDone)
        }
    }

    AlumnosListContent(
        alumnos = uiState.alumnos,
        isLoading = uiState.isLoading
    )
}

@Composable
fun AlumnosListContent(
    alumnos: List<Alumno> = emptyList(),
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
            this.items(items = alumnos, key = { alumno -> alumno.nombre }) { alumno ->
                AlumnoItem(alumno)
            }
        }
    }
}

@Composable
fun AlumnoItem(
    alumno: Alumno
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding8))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.6f)
                ) {
                    Text(
                        text = stringResource(R.string.name) + " " + alumno.nombre,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding4))
                    )
                    Text(
                        text = stringResource(R.string.dni) + " " + alumno.dni,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.4f)
                ) {
                    if (alumno.asignaturas == null )
                        Text(stringResource(R.string.sin_datos))
                    else
                        LazyRow {
                            this.items(
                                items = alumno.asignaturas,
                                key = { asignatura -> asignatura.codigo }) { asignatura ->
                                AsignaturaItem(asignatura)
                            }
                        }
                }
            }
        }
    }
}

@Composable
fun AsignaturaItem(
    asignatura: Asignatura = Asignatura()
) {
    Text(
        text = asignatura.nombre + ": " + asignatura.nota,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun AlumnosListScreenPreview() {

    AlumnosListContent(
        alumnos = listOf(
            Alumno(
                "dsfds", "asdsad", "123456", "fdsfsdf", listOf(
                    Asignatura("Lengua", "1234", 40, 7.4),
                    Asignatura("Mates", "1234", 40, 4.4)
                )
            ),
            Alumno(
                "gfhgfhfgh", "asdsad", "12hfghgf3456", "fdsfhgfhgfhfgsdf", listOf(
                    Asignatura("Lengua", "1234", 40, 7.4),
                    Asignatura("Mates", "1234", 40, 4.4)
                )
            )
        ),
        isLoading = false
    )

}