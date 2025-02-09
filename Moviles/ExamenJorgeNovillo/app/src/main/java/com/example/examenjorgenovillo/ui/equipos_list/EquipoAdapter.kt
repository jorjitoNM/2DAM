package com.example.examenjorgenovillo.ui.equipos_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.apptareas.R
import com.example.examenjorgenovillo.domain.model.Equipo

class EquipoAdapter(
    val actions: EquipoActions,
    val context: Context,
) : ListAdapter<Equipo, EquipoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipoViewHolder {
        return EquipoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.equipo_view, parent, false),
            actions,
        )
    }

    override fun onBindViewHolder(holder: EquipoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class DiffCallback : DiffUtil.ItemCallback<Equipo>() {
        override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
            return oldItem == newItem
        }
    }

    interface EquipoActions {
        fun onItemClick(event : Equipo)
    }
}