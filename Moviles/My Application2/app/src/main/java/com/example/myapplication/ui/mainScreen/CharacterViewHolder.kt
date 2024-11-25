package com.example.myapplication.ui.mainScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.R
import com.example.myapplication.databinding.CharacterViewBinding
import com.example.myapplication.domain.model.Character

class CharacterViewHolder(itemView: View, val actions: CharacterAdapter.CharacterActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = CharacterViewBinding.bind(itemView)

    fun bind(character: Character){
        with(binding) {
            name.text = character.name
            species.text = character.species
            image.load(character.image)

            itemView.setBackgroundResource(R.color.md_theme_primary)

            itemView.setOnLongClickListener{
                true
            }
            itemView.setOnClickListener {
                actions.onItemClick(character)
            }
        }
    }
}