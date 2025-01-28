package com.example.apptareascompose.ui.patients_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptareascompose.domain.model.Patient
import com.example.primeraapp.ui.common.UiEvent
import java.time.LocalDate

@Composable
fun PatientsListScreen(
    patientListViewModel: PatientListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    onNavigateDetail: (Int) -> Unit = {},
) {
    val uiState by patientListViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        patientListViewModel.handleEvent(PatientListEvents.GetAllPatients)
    }

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            patientListViewModel.handleEvent(PatientListEvents.EventDone)
        }
    }

    PatientListContent(
        patients = uiState.patients,
        onNavigateDetail = onNavigateDetail,
    )
}

@Composable
fun PatientListContent(
    patients: List<Patient>,
    onNavigateDetail: (Int) -> Unit,
) {
    LazyColumn {
        this.items(items = patients, key = { patient -> patient.id }) { patient ->
            PatientItem(
                patient = patient,
                onNavigateDetail = onNavigateDetail,
            )
        }
    }
}

@Composable
fun PatientItem(
    patient: Patient,
    onNavigateDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp).clickable(onClick = {onNavigateDetail(patient.id)}),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Nombre: ${patient.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "ID: ${patient.id}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = "Fecha de Nacimiento: ${patient.birthDate}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.secondary,
                thickness = 1.dp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Teléfono: ${patient.phone}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PHONE)
fun PreviewPatientListScreen() {
    PatientListContent(
        patients = listOf(
            Patient(1, "Juan", LocalDate.now(), "123-123-123", 145),
            Patient(2, "Julian", LocalDate.now(), "946-371-112", 620),
            Patient(3, "Paula", LocalDate.now(), "600-511-538", 410),
            Patient(4, "Marcos", LocalDate.now(), "617-332-158", 375)
        ),
        onNavigateDetail = {},
    )
}