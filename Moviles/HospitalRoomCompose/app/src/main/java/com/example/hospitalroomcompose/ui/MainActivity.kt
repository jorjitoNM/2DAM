package com.example.hospitalroomcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hospitalroomcompose.ui.navigation.Navigation
import com.example.hospitalroomcompose.ui.theme.HospitalRoomComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HospitalRoomComposeTheme {
                Navigation()
            }
        }
    }
}