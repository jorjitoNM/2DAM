package com.example.examen2evajorgenovillo.ui.ratones_list

import com.example.examen2evajorgenovillo.data.remote.api_services.RatonesService

interface RatonesListEvents {
    data object EventDone : RatonesListEvents
    data object GetAll : RatonesListEvents
}