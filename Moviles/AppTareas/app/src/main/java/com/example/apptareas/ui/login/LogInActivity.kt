package com.example.apptareas.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apptareas.R
import com.example.apptareas.databinding.LoginBinding
import com.example.apptareas.domain.model.User
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {

    private val viewModel: LogInViewModel by viewModels ()
    private val binding : LoginBinding by lazy {
        LoginBinding.inflate(layoutInflater).apply { setContentView(root) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observarState()
    }

    private fun observarState() {
        viewModel.uiState.observe(this@LogInActivity) { state ->
            binding.submit.setOnClickListener{
                    viewModel.handleEvent(LogInEvents.LogIn(
                        User(1,binding.passwordText.text.toString(),binding.usernameText.text.toString(),"123")))
            }
            if (state.logged) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(R.string.user_id.toString(), state.user.id)
                startActivity(intent)
            }
            state.event?.let { event ->
               if (event is UiEvent.ShowSnackbar) {
                    Snackbar.make(binding.root, event.message, Snackbar.LENGTH_SHORT).show()
                }
                viewModel.handleEvent(LogInEvents.ShowEvent)
            }
        }
    }
}