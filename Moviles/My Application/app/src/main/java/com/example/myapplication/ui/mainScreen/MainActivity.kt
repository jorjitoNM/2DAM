package com.example.myapplication.ui.mainScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.usecases.GetBooks
import com.example.myapplication.ui.common.MarginItemDecoration
import com.example.myapplication.ui.detailsScreen.DetailsActivity
import com.example.myapplication.ui.newBookScreen.NewBookActivity

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter


    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetBooks(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        events()
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBooks()
    }

    private fun observarState() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            adapter.submitList(state.books)
            adapter.notifyDataSetChanged()
        }
    }

    private fun configureRecyclerView() {

        adapter = BookAdapter(itemClick = { book ->
            navigateToDetail(book.id)

        },
            actions = object : BookAdapter.BookActions {
                override fun onItemClick(book: Book) {
                    navigateToDetail((book.id-1))
                }
            })

        binding.bookList.layoutManager = LinearLayoutManager(this)

        binding.bookList.adapter = adapter

        binding.bookList.addItemDecoration(
            MarginItemDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.margin
                )
            )
        )
    }


    private fun navigateToDetail(id: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun events() {
        with(binding) {
            add.setOnClickListener() {
                viewModel.add()
                navigateToNewBook()
            }
        }
    }

    private fun navigateToNewBook() {
        val intent = Intent(this, NewBookActivity::class.java)
        startActivity(intent)
    }
}