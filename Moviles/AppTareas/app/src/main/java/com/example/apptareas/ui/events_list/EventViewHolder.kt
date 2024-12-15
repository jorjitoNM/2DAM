package com.example.apptareas.ui.events_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apptareas.R
import com.example.apptareas.databinding.EventViewBinding
import com.example.apptareas.domain.model.Event

class EventViewHolder(itemView: View, val actions: EventAdapter.EventActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = EventViewBinding.bind(itemView)

    fun bind(event : Event){
        with(binding) {
            eventTitle.text = event.title
            eventId.text = event.id.toString()
            eventDescription.text = event.body
            eventImage.load(event.image) {
                size(300,500)
            }

            itemView.setBackgroundResource(R.color.md_theme_primary)

            itemView.setOnClickListener {
                actions.onItemClick(event)
            }

            update.setOnClickListener {
                actions.updateEvent(event)
            }

            delete.setOnClickListener {
                actions.deleteEvent(event)
            }
        }
    }
}