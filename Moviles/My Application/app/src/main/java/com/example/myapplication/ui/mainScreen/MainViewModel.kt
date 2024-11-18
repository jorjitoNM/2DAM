package com.example.myapplication.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.domain.usecases.GetSongs
import com.example.myapplication.domain.usecases.GetToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getSongs: GetSongs,
    private val getToken : GetToken,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent (event : MainEvents) {
        when (event) {
            is MainEvents.GetSongs -> getSongs(event.token)
        }
    }

    private fun getSongs(token : String) {
        viewModelScope.launch {
            when (val networkResult = getSongs.invoke(token)) {
                is NetworkResult.Error -> TODO()
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> {
                    val songs = networkResult.data.toList()
                    _uiState.value = _uiState.value?.copy(songs = songs)
                }
            }
        }
    }

    fun getToken () : String {
        viewModelScope.launch {
            when (val networkResult = getToken.invoke()) {
                is NetworkResult.Error -> TODO()
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> {
                    return@launch networkResult.let { it.data }
                }
            }
        }
        return ""
    }
}