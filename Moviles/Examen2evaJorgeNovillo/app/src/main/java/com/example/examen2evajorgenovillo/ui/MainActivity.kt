package com.example.examen2evajorgenovillo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.examen2evajorgenovillo.ui.navigation.Navigation
import com.example.examen2evajorgenovillo.ui.theme.Examen2evaJorgeNovilloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Examen2evaJorgeNovilloTheme {
                Navigation()
            }
        }
    }
}

//fun <T> parseErrorResponse(errorBody: ResponseBody): NetworkResult<T> {
//    return try {
//        val errorBodyString = errorBody.string()
//        val apiError = Gson().fromJson(errorBodyString, ApiError::class.java)
//        NetworkResult.Error(apiError.message)
//    } catch (e: Exception) {
//        NetworkResult.Error(e.message ?: e.toString())
//    }
//}