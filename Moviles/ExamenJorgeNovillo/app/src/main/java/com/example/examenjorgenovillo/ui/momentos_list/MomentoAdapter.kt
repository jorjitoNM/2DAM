package com.example.examenjorgenovillo.ui.momentos_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.apptareas.R
import com.example.examenjorgenovillo.domain.model.Momento

class MomentoAdapter(
    val actions: MomentoActions,
    val context: Context,
) : ListAdapter<Momento, MomentoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentoViewHolder {
        return MomentoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.momento_view, parent, false),
            actions,
        )
    }

    override fun onBindViewHolder(holder: MomentoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class DiffCallback : DiffUtil.ItemCallback<Momento>() {
        override fun areItemsTheSame(oldItem: Momento, newItem: Momento): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Momento, newItem: Momento): Boolean {
            return oldItem == newItem
        }
    }
    interface MomentoActions {
        fun onItemClick(momento : Momento)
    }
}