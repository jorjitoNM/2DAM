package com.example.apptareas.ui.todos_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import com.example.apptareas.domain.model.Todo
import com.example.primerxmlmvvm.R

class TodoAdapter() : ListAdapter<Todo, TodoViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_view, parent, false),
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class DiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
}