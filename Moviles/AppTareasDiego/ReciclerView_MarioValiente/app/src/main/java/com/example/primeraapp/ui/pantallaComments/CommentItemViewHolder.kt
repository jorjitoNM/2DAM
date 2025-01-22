package com.example.primeraapp.ui.pantallaComments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.primeraapp.databinding.CommentsViewBinding
import com.example.primeraapp.domain.modelo.Comment

class CommentItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = CommentsViewBinding.bind(itemView)

    fun bind(item: Comment) {
        with(binding) {
            name.text = item.name
            body.text = item.body
            itemView.setBackgroundResource(android.R.color.white)
        }
    }
}