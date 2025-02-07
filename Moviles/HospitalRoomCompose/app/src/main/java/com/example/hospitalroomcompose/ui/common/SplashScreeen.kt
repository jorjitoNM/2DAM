package com.example.hospitalroomcompose.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hospitalroomcompose.R

@Composable
fun SplashScreen () {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "App Logo",
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED)
fun SplashScreenPreview () {
    SplashScreen()
}