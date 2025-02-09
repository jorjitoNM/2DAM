package com.example.primeraapp.ui.loginActivity


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.primeraapp.databinding.ActivityLoginBinding
import com.example.primeraapp.ui.common.ConstantesUI
import com.example.primeraapp.ui.common.UiEvent
import com.example.primeraapp.ui.postActivity.PostActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventos()
        observeState()
    }

    private fun eventos() {
        binding.botonRegistro.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            viewModel.handleEvent(LoginEvent.registerUser(username, password))
        }
        binding.botonInicioSesion.setOnClickListener {
            val username = binding.inputUsername.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            viewModel.handleEvent(LoginEvent.validateUser(username, password))
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.validado) {
                        navigateToMain(state.userId)
                    }
                    state.event?.let { event ->
                        when (event) {
                            is UiEvent.ShowSnackbar -> UiEvent.ShowSnackbar(event.message)
                            is UiEvent.PopBackStack -> true
                        }
                    }
                }
            }
        }
    }

    private fun navigateToMain(userId: Int) {
        val intent = Intent(this, PostActivity::class.java).apply {
            putExtra(ConstantesUI.USERID, userId)
        }
        startActivity(intent)
        finish()
    }

}
