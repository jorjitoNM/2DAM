package com.example.myapplication.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.NetworkResult
import com.example.myapplication.domain.usecases.GetSongs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val getSongs: GetSongs,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent (event : MainEvents) {
        when (event) {
            is MainEvents.GetSongs -> getSongs()
        }
    }

    private fun getSongs() {
        viewModelScope.launch {
            when (val networkResult = getSongs.invoke()) {
                is NetworkResult.Error -> TODO()
                is NetworkResult.Loading -> TODO()
                is NetworkResult.Success -> {
                    val songs = networkResult.data?.toList() ?: emptyList()
                    _uiState.value = _uiState.value?.copy(songs = songs)
                }
            }
        }
    }
}