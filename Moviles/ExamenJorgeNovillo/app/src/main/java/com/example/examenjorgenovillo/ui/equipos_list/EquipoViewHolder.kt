package com.example.examenjorgenovillo.ui.equipos_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apptareas.databinding.EquipoViewBinding
import com.example.examenjorgenovillo.domain.model.Equipo

class EquipoViewHolder(itemView: View, val actions: EquipoAdapter.EquipoActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = EquipoViewBinding.bind(itemView)

    fun bind(equipo : Equipo){
        with(binding) {
            equipoName.text = equipo.nombre
            equipoId.text = equipo.id.toString()

            itemView.setOnLongClickListener{
                true
            }
            itemView.setOnClickListener {
                actions.onItemClick(equipo)
            }
        }
    }
}