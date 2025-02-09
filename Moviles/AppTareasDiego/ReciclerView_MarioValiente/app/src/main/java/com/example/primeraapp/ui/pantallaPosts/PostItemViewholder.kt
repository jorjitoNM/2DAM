package com.example.primeraapp.ui.pantallaPosts

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.primeraapp.databinding.PostsViewBinding
import com.example.primeraapp.domain.modelo.Post

class PostItemViewholder(itemView: View, private val actions: PostAdapter.PostActions) :
    RecyclerView.ViewHolder(itemView) {
    private val binding = PostsViewBinding.bind(itemView)
    fun bind(item: Post) {
        with(binding) {
            cardTitle.text = item.title
            cardSubtitle.text = item.body
            itemView.setBackgroundResource(android.R.color.white)
            itemView.setOnLongClickListener {
                true
            }
            itemView.setOnClickListener {
                actions.onItemClick(item)
            }
        }
    }
}