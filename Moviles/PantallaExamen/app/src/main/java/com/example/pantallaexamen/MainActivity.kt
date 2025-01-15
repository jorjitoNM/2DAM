package com.example.pantallaexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        Column (modifier = Modifier.padding(padding).background(Color.Black)) {
            Row (
                modifier =  Modifier.weight(0.15f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.3f)
                ) {
                    Button(onClick = {},
                        modifier = Modifier.clip(CircleShape),
                        colors = ButtonColors(Color.Black,Color.White,Color.White, Color.Black),
                        border = BorderStroke(5.dp, Color.White),
                    ) {
                        Icon(Icons.Default.PlayArrow, "Play")
                    }
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.4f)
                ) {
                    Text(
                        text = "10 : 00",
                        color = Color.Red,
                        fontSize = 45.sp,
                    )
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.3f)
                ) {
                    Text(
                        text = "1º",
                        fontSize = 30.sp,
                        color = Color.White,
                    )
                }

            }
            Row (
                modifier = Modifier.weight(0.3f)
            ) {
                Column (
                    modifier = Modifier.weight(0.2f)
                ) {
                    Row {
                        PlusButton("+1",Modifier.clip(CircleShape))
                    }
                     Row {
                         PlusButton("+2",Modifier.clip(CircleShape))
                     }
                    Row {
                        PlusButton("+3",Modifier.clip(CircleShape))
                    }
                }
                Column (
                    modifier = Modifier.weight(0.8f)
                ) {
                    TeamBox(
                    "Equipo 1",
                        34,
                    )
                }
            }
            Row(
                modifier = Modifier.weight(0.1f),
                horizontalArrangement = Arrangement.Center,
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text (
                        text = "x",
                        fontSize = 40.sp,
                        color = Color.White,
                    )
                }
            }
            Row (
                modifier = Modifier.weight(0.3f)
            ) {
                Column (
                    modifier = Modifier.weight(0.2f)
                ) {
                    Row {
                        PlusButton("+1",Modifier.clip(CircleShape))
                    }
                    Row {
                        PlusButton("+2",Modifier.clip(CircleShape))
                    }
                    Row {
                        PlusButton("+3",Modifier.clip(CircleShape))
                    }
                }
                Column (
                    modifier = Modifier.weight(0.8f)
                ) {
                    TeamBox(
                        "Equipo 2",
                        18,
                    )
                }
            }
        }
    }
}

fun plusTeamA () {

}

@Composable
fun PlusButton (text : String = "0", modifier: Modifier = Modifier) {
    Button(
        onClick = { plusTeamA() },
        modifier = modifier,
        colors = ButtonColors(Color.White,Color.Black,Color.Black,Color.White),
    ) {
        Text (text = text,
            fontSize = 15.sp,
        )
    }
}

@Composable
fun TeamBox (teamName : String = "Equipo", score : Int = 0, modifier: Modifier = Modifier) {
    Box (modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        Text (
            text = teamName,
            modifier = Modifier.align(Alignment.TopCenter),
            fontSize = 35.sp,
            color = Color.White,
        )
        Text (
            text = score.toString(),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 75.sp,
            color = Color.Green,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreenPortrait(Modifier)
}