package com.example.examenjorgenovillo.ui.momentos_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apptareas.databinding.MomentoViewBinding
import com.example.examenjorgenovillo.domain.model.Momento

class MomentoViewHolder(itemView: View, val actions: MomentoAdapter.MomentoActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = MomentoViewBinding.bind(itemView)

    fun bind(momento : Momento){
        with(binding) {
            tiempo.text = momento.tiempo
            cuarto.text = momento.cuarto.toString()
            local.text = momento.equipoCasa
            visitante.text = momento.equipoFuera
            marcadorLocal.text = momento.marcadorEquipoCasa.toString()
            marcadorVisitante.text = momento.marcadorEquipoFuera.toString()
        }

        itemView.setOnLongClickListener{
            true
        }
        itemView.setOnClickListener {
            actions.onItemClick(momento)
        }
    }
}