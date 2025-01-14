package com.example.pantallaexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pantallaexamen.ui.theme.PantallaExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaExamenTheme {
                MainScreenPortrait(Modifier)
            }
        }
    }
}

@Composable
fun MainScreenPortrait(modifier: Modifier = Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column {
            Row (
                modifier =  Modifier.weight(0.2f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.33f)
                ) {
                    Button(onClick = {},
                        modifier = Modifier.clip(CircleShape)
                    ) {
                        Icon(Icons.Default.PlayArrow, "Play")
                    }
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.33f)
                ) {
                    Text(
                        text = "10 : 00",
                        color = Color.Red,
                        fontSize = 30.sp,
                    )
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.33f)
                ) {
                    Text(
                        text = "1º",
                        fontSize = 20.sp
                    )
                }

            }
            Row {
                Column (
                    modifier = Modifier.weight(0.15f)
                ) {

                }
                Column (
                    modifier = Modifier.weight(0.85f)
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreenPortrait(Modifier)
}