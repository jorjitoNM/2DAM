package com.example.myapplication.ui.mainScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.R
import com.example.myapplication.domain.model.Song

class SongAdapter(
    val actions: SongActions,
    val context: Context,
) : ListAdapter<Song, SongViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.song_view, parent, false),
            actions,
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class DiffCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    interface SongActions {
        fun onItemClick(song: Song)
    }
}