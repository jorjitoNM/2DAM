package com.example.hospitalroomcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.hospitalroomcompose.ui.navigation.Navigation
import com.example.hospitalroomcompose.ui.splash_screen.SplashScreen
import com.example.hospitalroomcompose.ui.theme.HospitalRoomComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HospitalRoomComposeTheme {
                var showSplash by rememberSaveable { mutableStateOf(true) }
                if (showSplash) {
                    SplashScreen(
                        onLoaded = { showSplash = false }
                    )
                } else {
                    Navigation()
                }
            }
        }
    }
}