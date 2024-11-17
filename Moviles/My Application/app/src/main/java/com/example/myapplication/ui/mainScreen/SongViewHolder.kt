package com.example.myapplication.ui.mainScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.databinding.SongViewBinding
import com.example.myapplication.domain.model.Song

class SongViewHolder(itemView: View, val actions: SongAdapter.SongActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = SongViewBinding.bind(itemView)

    fun bind(song: Song){
        with(binding) {
            listSongName.text = song.name
            listSongArtist.text = parseArtist(song.artist)
            image.load(song.albumImage)

            itemView.setBackgroundResource(R.color.md_theme_primary)

            itemView.setOnLongClickListener{
                true
            }
            itemView.setOnClickListener {
                actions.onItemClick(song)
            }
        }
    }
    fun parseArtist (artist : List<String>) : String {
         val sb : StringBuilder = StringBuilder()
        artist.forEach { a -> sb.append(a).append(", ") }
        return sb.toString()
    }
}