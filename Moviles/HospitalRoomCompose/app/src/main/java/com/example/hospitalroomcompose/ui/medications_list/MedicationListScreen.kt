package com.example.hospitalroomcompose.ui.medications_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptareascompose.domain.model.Medication
import com.example.primeraapp.ui.common.UiEvent

@Composable
fun MedicationListScreen (
    medicationsListViewModel : MedicationsListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit,
) {
    val uiState by medicationsListViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        medicationsListViewModel.handleEvent(MedicationListEvents.GetAllMedications)
    }

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            medicationsListViewModel.handleEvent(MedicationListEvents.EventDone)
        }
    }
}

@Composable
fun MedicationsListContent (
    medications : List<Medication> = emptyList(),

) {
    LazyColumn {
        this.items(items = medications, key = {medication -> medication.id}) { medication ->
            MedicationItem(medication)
        }
    }
}

@Composable
fun MedicationItem (
    medication: Medication,
) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
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
                Column {
                    Text(
                        text = "Nombre: ${medication.medicationName}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "ID: ${medication.id}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
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
                    text = "Dosage: ${medication.dosage}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun MedicationsListContentPreview () {
    MedicationsListContent(
        medications = listOf(Medication())
    )
}