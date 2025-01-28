package com.example.apptareascompose.ui.doctors_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apptareascompose.domain.model.Doctor
import com.example.primeraapp.ui.common.Constantes
import com.example.primeraapp.ui.common.UiEvent

@Composable
fun DoctorsListScreen(
    doctorsListViewModel: DoctorsListViewModel = hiltViewModel(),
    showSnackbar: (String) -> Unit = {}
) {
    val uiState by doctorsListViewModel.uiState.collectAsState()
    LaunchedEffect(key1 = Unit) {
        doctorsListViewModel.handleEvent(DoctorListEvents.GetAllDoctors)
    }

    LaunchedEffect(uiState.uiEvent) {
        uiState.uiEvent?.let {
            if (it is UiEvent.ShowSnackbar) {
                showSnackbar(it.message)
            }
            doctorsListViewModel.handleEvent(DoctorListEvents.EventDone)
        }
    }

    DoctorsListContent(
        doctors = uiState.doctors
    )
}

@Composable
fun DoctorsListContent(
    doctors: List<Doctor>,
) {
    LazyColumn {
        this.items(
            items = doctors,
            key = { doctor -> doctor.id }) { doctor ->
            DoctorItem(
                doctor = doctor,
            )
        }
    }
}

@Composable
fun DoctorItem(doctor: Doctor) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = Constantes.NAME + doctor.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = Constantes.ID + " ${doctor.id}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
