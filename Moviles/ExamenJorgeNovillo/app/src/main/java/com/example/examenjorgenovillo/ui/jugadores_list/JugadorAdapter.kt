package com.example.examenjorgenovillo.ui.jugadores_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.apptareas.R
import com.example.examenjorgenovillo.domain.model.Jugador

class JugadorAdapter(
    val context: Context,
) : ListAdapter<Jugador, JugadorViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadorViewHolder {
        return JugadorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.equipo_view, parent, false),
        )
    }

    override fun onBindViewHolder(holder: JugadorViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class DiffCallback : DiffUtil.ItemCallback<Jugador>() {
        override fun areItemsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
            return oldItem.dorsal == newItem.dorsal
        }

        override fun areContentsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
            return oldItem == newItem
        }
    }
}