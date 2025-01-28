package com.example.apptareascompose.ui.medical_record_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptareascompose.domain.model.MedicalRecord
import com.example.primeraapp.ui.common.UiEvent
import java.time.LocalDate


@Composable
fun MedicalRecordDetailsScreen (
    recordId: Int = 1,
    medicalRecordDetailsViewModel : MedicalRecordDetailsViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
) {
    val uiState by medicalRecordDetailsViewModel.uiState.collectAsState()
    LaunchedEffect(key1 = Unit) {
        medicalRecordDetailsViewModel.handleEvent(MedicalRecordDetailsEvents.GetMedicalRecords(recordId))
    }

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            medicalRecordDetailsViewModel.handleEvent(MedicalRecordDetailsEvents.EventDone)
        }
    }

    MedicalRecordDetailsContent(
        medicalRecord = uiState.medicalRecord,
        loading = uiState.isLoading
    )

}

@Composable
fun MedicalRecordDetailsContent (
    medicalRecord : MedicalRecord,
    loading: Boolean,
) {
    Column( modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(0.1f))
        Row (modifier = Modifier.fillMaxWidth().weight(0.2f)) {
            Column (modifier = Modifier.fillMaxSize().weight(0.9f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = medicalRecord.description,
                    onValueChange = { newText -> newText },
                    label = { Text("Description") },
                    singleLine = true
                )
            }
            Column (modifier = Modifier.fillMaxSize().weight(0.1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start) {
                Text(medicalRecord.id.toString())
            }
        }
        Row (modifier = Modifier.fillMaxWidth().weight(0.2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Column (modifier = Modifier.fillMaxSize().weight(0.9f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = medicalRecord.medications.toString(),
                    onValueChange = { newText -> newText },
                    label = { Text("Medications") },
                    singleLine = true
                )
            }
            Column (modifier = Modifier.fillMaxSize().weight(0.1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
        Row (modifier = Modifier.fillMaxWidth().weight(0.2f)) {
            Column (modifier = Modifier.fillMaxSize().weight(0.9f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = medicalRecord.date.toString(),
                    onValueChange = { newText -> newText },
                    label = { Text("Date") },
                    singleLine = true
                )
            }
            Column (modifier = Modifier.fillMaxSize().weight(0.1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start) {
                Spacer(modifier = Modifier.weight(0.1f))
            }
        }
        Row (modifier = Modifier.fillMaxWidth().weight(0.1f)) {
            Column (modifier = Modifier.fillMaxSize().weight(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Doctor ID: " + medicalRecord.doctorId.toString())
            }
            Column (modifier = Modifier.fillMaxSize().weight(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Patient ID: " + medicalRecord.patientId.toString())
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
    }

}

@Composable
@Preview(showBackground = true, device = Devices.PHONE)
fun PreviewMedicalRecordDetailsScreen () {
    MedicalRecordDetailsContent(
        MedicalRecord(1,"Cancer", LocalDate.now(),12,3, listOf("DiacetilMorphine")),false
    )
}
