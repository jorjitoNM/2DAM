package com.example.apptareascompose.ui.medical_records_list

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptareascompose.domain.model.MedicalRecord
import com.example.apptareascompose.domain.model.Medication
import com.example.apptareascompose.domain.model.Patient
import com.example.apptareascompose.ui.patients_list.PatientListContent
import java.time.LocalDate

@Composable
fun MedicalRecordListScreen(
    patientId: Int = 1,
    medicalRecordListViewModel: MedicalRecordListViewModel = hiltViewModel(),
    showSnackbar: (String, () -> Unit) -> Unit,
    onNavigateDetalle: (String) -> Unit = {},
) {
    val uiState by medicalRecordListViewModel.uiState.collectAsState()
    var undo by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        medicalRecordListViewModel.handleEvent(MedicalRecordListEvents.GetAllMedicalRecord(patientId))
    }
}

@Composable
fun MedicalRecordListContent(
    medicalRecords: List<MedicalRecord>,
    onNavigateDetail: (String) -> Unit,
    loading: Boolean,
) {
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
}

@Composable
fun MedicalRecordItem(
    medicalRecord: MedicalRecord,
    onNavigateDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
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
        medicalRecords = listOf(
            MedicalRecord(
                id = 1,
                description = "Routine checkup with no major issues.",
                date = LocalDate.of(2025, 1, 20),
                patientId = 101,
                doctorId = 201,
                medications = listOf(
                    Medication(id = 1, medicationName = "Vitamin D", medRecordId = 1, dosage = "1000 IU")
                )
            ),
            MedicalRecord(
                id = 2,
                description = "Follow-up visit for hypertension treatment.",
                date = LocalDate.of(2025, 1, 15),
                patientId = 102,
                doctorId = 202,
                medications = listOf(
                    Medication(id = 2, medicationName = "Lisinopril", medRecordId = 2, dosage = "10 mg"),
                    Medication(id = 3, medicationName = "Hydrochlorothiazide", medRecordId = 2, dosage = "25 mg")
                )
            ),
            MedicalRecord(
                id = 3,
                description = "Post-surgery recovery for knee replacement.",
                date = LocalDate.of(2025, 1, 10),
                patientId = 103,
                doctorId = 203,
                medications = listOf(
                    Medication(id = 4, medicationName = "Ibuprofen", medRecordId = 3, dosage = "400 mg"),
                    Medication(id = 5, medicationName = "Oxycodone", medRecordId = 3, dosage = "5 mg")
                )
            )
        ),
        onNavigateDetail = {},
        loading = true,
    )
}