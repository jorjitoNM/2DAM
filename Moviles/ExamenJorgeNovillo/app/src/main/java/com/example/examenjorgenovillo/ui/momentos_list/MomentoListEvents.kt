package com.example.examenjorgenovillo.ui.momentos_list

sealed interface MomentoListEvents {
    data class getMomentos (val equipoId : Int) : MomentoListEvents
    data object eventDone : MomentoListEvents
}