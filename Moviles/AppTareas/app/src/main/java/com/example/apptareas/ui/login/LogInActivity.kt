package com.example.apptareas.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.example.apptareas.domain.model.User
import com.example.apptareas.ui.common.UiEvent
import com.example.apptareas.ui.main.MainActivity
import com.example.apptareas.utilities.Constantes
import com.example.primerxmlmvvm.databinding.LoginBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {

    private val viewModel: LogInViewModel by viewModels ()
    private val binding : LoginBinding by lazy {
        LoginBinding.inflate(layoutInflater).apply { setContentView(root) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.login) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observarState()
        binding.submit.setOnClickListener{
            viewModel.handleEvent(LogInEvents.LogIn(
                User(1,binding.passwordText.text.toString(),binding.usernameText.text.toString(),"")))
        }
    }

    private fun observarState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.logged) {
                        val intent = Intent(this@LogInActivity, MainActivity::class.java)
                        intent.putExtra(Constantes.USER_ID, state.user.id)
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
    }
}