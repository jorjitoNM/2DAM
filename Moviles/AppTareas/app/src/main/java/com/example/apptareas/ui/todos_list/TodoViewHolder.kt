package com.example.apptareas.ui.todos_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.apptareas.R
import com.example.apptareas.databinding.TodoViewBinding
import com.example.apptareas.domain.model.Todo

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = TodoViewBinding.bind(itemView)

    fun bind(todo : Todo){
        with(binding) {
            title.text = todo.title
            done.isChecked = todo.completed

            itemView.setBackgroundResource(R.color.md_theme_primary)
        }
    }
}