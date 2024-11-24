package com.example.apptareas.ui.eventsList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apptareas.R
import com.example.apptareas.databinding.EventViewBinding
import com.example.apptareas.domain.model.Event

class EventViewHolder(itemView: View, val actions: EventAdapter.SongActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = EventViewBinding.bind(itemView)

    fun bind(event : Event){
        with(binding) {
            eventTitle.text = event.title
            eventId.text = event.id.toString()
            eventImage.load(event.image)

            itemView.setBackgroundResource(R.color.md_theme_primary)

            itemView.setOnLongClickListener{
                true
            }
            itemView.setOnClickListener {
                actions.onItemClick(event)
            }
        }
    }
}