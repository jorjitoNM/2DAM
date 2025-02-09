package com.example.hospitalroomcompose.ui.splash_screen

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalroomcompose.R
import com.example.hospitalroomcompose.ui.navigation.LoginScreenDestination
import com.example.hospitalroomcompose.ui.navigation.SplashScreenDestination

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController,
) {
    val isLoading = viewModel.isLoading.collectAsState()

    LaunchedEffect(isLoading) {
        if (!isLoading.value) {
            navController.navigate(LoginScreenDestination) {
                popUpTo(SplashScreenDestination) { inclusive = true }
            }
        }
    }

    SplashScreenContent()

}

@Composable
fun SplashScreenContent () {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.weight(0.85f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_screen),
                contentDescription = "App Logo",
                modifier = Modifier.fillMaxSize(),
            )
        }
        Row(
            modifier = Modifier.weight(0.15f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            JumpingDotsAnimation()
        }
    }
}

@Composable
fun JumpingDotsAnimation() {
    val dotSize = 20.dp
    val jumpHeight = 30.dp
    val animationDuration = 600
    val color = MaterialTheme.colorScheme.primaryContainer

    listOf(0, 1, 2).forEach { index ->
        JumpingDot(
            jumpHeight = jumpHeight,
            dotSize = dotSize,
            color = color,
            animationDuration = animationDuration,
            delay = index * (animationDuration / 3)
        )

        if (index != 2) Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun JumpingDot(
    jumpHeight: androidx.compose.ui.unit.Dp,
    dotSize: androidx.compose.ui.unit.Dp,
    color: Color,
    animationDuration: Int,
    delay: Int
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -jumpHeight.value,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                delayMillis = delay,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Surface(
        modifier = Modifier
            .size(dotSize)
            .offset(y = yOffset.dp),
        shape = CircleShape,
        color = color,
    ) {}
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED)
fun SplashScreenPreview() {
    SplashScreenContent()
}