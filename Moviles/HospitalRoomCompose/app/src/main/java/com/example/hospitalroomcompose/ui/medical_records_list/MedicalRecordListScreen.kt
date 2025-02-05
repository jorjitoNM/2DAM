package com.example.hospitalroomcompose.ui.medical_records_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptareascompose.domain.model.MedicalRecord
import com.example.apptareascompose.ui.medical_records_list.MedicalRecordListEvents
import com.example.primeraapp.ui.common.Constantes
import com.example.primeraapp.ui.common.UiEvent
import java.time.LocalDate

@Composable
fun MedicalRecordListScreen(
    patientId: Int = 1,
    medicalRecordListViewModel: MedicalRecordListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
    onNavigateDetalle: (Int) -> Unit = {},
    onNavigateEmptyDetails : () -> Unit = {},
) {
    val uiState by medicalRecordListViewModel.uiState.collectAsState()
    LaunchedEffect(key1 = Unit) {
        medicalRecordListViewModel.handleEvent(MedicalRecordListEvents.GetAllMedicalRecord(patientId))
    }

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            medicalRecordListViewModel.handleEvent(MedicalRecordListEvents.EventDone)
        }
    }

    MedicalRecordListContent(
        patientName = uiState.patientName,
        medicalRecords = uiState.medicalRecords,
        onNavigateDetail = onNavigateDetalle,
        onNavigateEmptyDetails = onNavigateEmptyDetails,
    )
}

@Composable
fun MedicalRecordListContent(
    patientName : String = Constantes.PATIENT,
    medicalRecords: List<MedicalRecord>,
    onNavigateDetail: (Int) -> Unit,
    onNavigateEmptyDetails: () -> Unit,
) {
    Column {
        Row (modifier = Modifier.fillMaxSize().weight(0.1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            ) {
            Text(
                color = MaterialTheme.colorScheme.primary,
                fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                text = patientName,
                modifier = Modifier,
                fontSize = 35.sp)
        }
        Row (modifier = Modifier.fillMaxSize().weight(0.9f))  {
            LazyColumn {
                this.items(
                    items = medicalRecords,
                    key = { medicalRecord -> medicalRecord.id }) { medicalRecord ->
                    MedicalRecordItem(
                        medicalRecord = medicalRecord,
                        onNavigateDetail = onNavigateDetail,
                    )
                }
            }
            FloatingActionButton(
                onClick = onNavigateEmptyDetails
            ) { }
        }
    }
}

@Composable
fun MedicalRecordItem(
    medicalRecord: MedicalRecord,
    onNavigateDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp).clickable(onClick = { onNavigateDetail(medicalRecord.id) }),
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
                        text = "Diagnosis: ${medicalRecord.description}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "ID: ${medicalRecord.id}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = "Fecha: ${medicalRecord.date}",
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
                    text = "Medicacion: ${medicalRecord.medications}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PHONE)
fun PreviewMedicalRecordListScreen() {
    MedicalRecordListContent(
        patientName = "Yotoko Tutoto",
        medicalRecords = listOf(
            MedicalRecord(
                id = 1,
                description = "Routine checkup with no major issues.",
                date = LocalDate.of(2025, 1, 20),
                patientId = 101,
                doctorId = 201,
                medications = listOf("Vitamin D")
            ),
            MedicalRecord(
                id = 2,
                description = "Follow-up visit for hypertension treatment.",
                date = LocalDate.of(2025, 1, 15),
                patientId = 101,
                doctorId = 202,
                medications = listOf("Lisinopril","Hydrochlorothiazide"),
            ),
            MedicalRecord(
                id = 3,
                description = "Post-surgery recovery for knee replacement.",
                date = LocalDate.of(2025, 1, 10),
                patientId = 101,
                doctorId = 203,
                medications = listOf("Ibuprofen","Oxycodone")
        ),),
        onNavigateDetail = {},
        onNavigateEmptyDetails = {},
    )
}