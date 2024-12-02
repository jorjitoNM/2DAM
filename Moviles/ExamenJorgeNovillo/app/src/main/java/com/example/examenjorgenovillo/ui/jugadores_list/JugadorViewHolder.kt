package com.example.examenjorgenovillo.ui.jugadores_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apptareas.databinding.JugadorViewBinding
import com.example.examenjorgenovillo.domain.model.Jugador

class JugadorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = JugadorViewBinding.bind(itemView)

    fun bind(jugador : Jugador){
        with(binding) {
            name.text = jugador.nombre
            surename.text = jugador.apellido
            dorsal.text = jugador.dorsal.toString()
        }
    }
}