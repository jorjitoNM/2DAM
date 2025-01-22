package com.example.primeraapp.ui.pantallaPosts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.primeraapp.R
import com.example.primeraapp.domain.modelo.Post

class PostAdapter(
    val actions: PostActions,
) : ListAdapter<Post, PostItemViewholder>(DiffCallback()) {


    private var allPosts: List<Post> = listOf()


    fun setFullData(data: List<Post>) {
        allPosts = data
        submitList(data)
    }


    fun filterData(query: String) {
        val filteredPosts = allPosts.filter { post ->
            post.title.contains(query, ignoreCase = true) || post.body.contains(
                query,
                ignoreCase = true
            )
        }
        submitList(filteredPosts)
    }

    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewholder {
        return PostItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.posts_view, parent, false),
            actions,
        )
    }

    override fun onBindViewHolder(holder: PostItemViewholder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun interface PostActions {
        fun onItemClick(post: Post)
    }
}