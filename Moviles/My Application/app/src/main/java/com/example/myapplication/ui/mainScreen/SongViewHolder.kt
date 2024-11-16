package com.example.myapplication.ui.mainScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.SongViewBinding
import com.example.myapplication.domain.model.Song

class SongViewHolder(itemView: View, val actions: SongAdapter.SongActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = SongViewBinding.bind(itemView)

    fun bind(song: Song){
        with(binding) {
            listSongName.text = song.name
            listSongArtist.text = song.artist //parse the artist int a list

            itemView.setBackgroundResource(R.color.md_theme_primary)

            itemView.setOnLongClickListener{
                true
            }
            itemView.setOnClickListener {
                actions.onItemClick(song)
            }
        }
    }
}