package com.example.primeraapp.ui.pantallaPostsOfUser

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.primeraapp.R
import com.example.primeraapp.domain.modelo.Post

class PostUserAdapter(
    val context: Context,
    val actions: PostActions,
) : ListAdapter<Post, PostUserItemViewholder>(DiffCallback()) {


    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostUserItemViewholder {
        return PostUserItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.posts_view, parent, false),
            actions,
        )
    }

    override fun onBindViewHolder(holder: PostUserItemViewholder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun interface PostActions {
        fun onDelete(post: Post)
    }

    val swipeGesture = object : SwipeGesture(context) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (direction == ItemTouchHelper.RIGHT || direction == ItemTouchHelper.LEFT) {
                val position = viewHolder.adapterPosition
                val postToDelete = currentList[position]
                notifyItemRemoved(position)
                actions.onDelete(postToDelete)
            }
        }
    }
}