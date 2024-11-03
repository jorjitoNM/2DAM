package com.example.myapplication.ui.mainScreen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.domain.model.Book
import com.example.viewmodel.R
import com.example.viewmodel.databinding.BookViewBinding

class BookViewHolder(itemView: View, val actions: BookAdapter.BookActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = BookViewBinding.bind(itemView)

    fun bind(book: Book){
        with(binding) {
            listBookName.text = book.name
            listBookAuthor.text = book.author

            itemView.setBackgroundResource(R.color.md_theme_primary)

            itemView.setOnLongClickListener{
                true
            }
            itemView.setOnClickListener {
                actions.onItemClick(book)
            }
        }
    }
}