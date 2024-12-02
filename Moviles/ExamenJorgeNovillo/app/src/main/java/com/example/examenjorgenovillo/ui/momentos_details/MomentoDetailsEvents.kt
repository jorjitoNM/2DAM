package com.example.examenjorgenovillo.ui.momentos_details

interface MomentoDetailsEvents {
    data class getMomento (val momentoId : Int) : MomentoDetailsEvents
    data object  eventDone : MomentoDetailsEvents
}