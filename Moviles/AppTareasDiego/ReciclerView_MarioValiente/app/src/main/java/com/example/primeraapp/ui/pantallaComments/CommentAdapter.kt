package com.example.primeraapp.ui.pantallaComments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.primeraapp.R
import com.example.primeraapp.domain.modelo.Comment

class CommentAdapter : ListAdapter<Comment, CommentItemViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {
        return CommentItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.comments_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}