package com.example.pantallaexamen

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pantallaexamen.ui.theme.PantallaExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val configuration = LocalConfiguration.current

            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    PantallaExamenTheme {
                        MainScreenPortrait(Modifier)
                    }
                }
                Configuration.ORIENTATION_LANDSCAPE -> {
                    PantallaExamenTheme {
                        MainScreenLandscape(Modifier)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreenPortrait(modifier: Modifier = Modifier) {
    var puntosA by remember { mutableIntStateOf(34) }
    var puntosB by remember { mutableIntStateOf(18) }
    var isEdit by remember { mutableStateOf(false) }
    var sign by remember { mutableStateOf("+") }
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier.weight(0.15f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.3f)
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.clip(CircleShape),
                        colors = ButtonColors(Color.Black, Color.White, Color.White, Color.Black),
                        border = BorderStroke(5.dp, Color.White),
                    ) {
                        Icon(Icons.Default.PlayArrow, stringResource(R.string.play))
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.4f)
                ) {
                    Text(
                        text = "10 : 00",
                        color = Color.Red,
                        fontSize = 45.sp,
                    )
                }
                Column(
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
            Row(
                modifier = Modifier.weight(0.3f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(0.25f).fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row (
                        modifier = Modifier.weight(0.33f).fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        PlusButton(
                            Modifier
                                .clip(CircleShape)
                                .padding(4.dp, 0.dp),
                            {
                                if (isEdit) {
                                    puntosA -= 1
                                } else {
                                    puntosA += 1
                                }
                            },
                            sign + "1",
                        )
                    }
                    Row (
                        modifier = Modifier.weight(0.33f).fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        PlusButton(
                            Modifier
                                .clip(CircleShape)
                                .padding(4.dp, 0.dp),
                            {
                                if (isEdit) {
                                    puntosA -= 2
                                } else {
                                    puntosA += 2
                                }
                            },
                            sign + "2",
                        )
                    }
                    Row (
                        modifier = Modifier.weight(0.33f).fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        PlusButton(
                            Modifier
                                .clip(CircleShape)
                                .padding(4.dp, 0.dp),
                            {
                                if (isEdit) {
                                    puntosA -= 3
                                } else {
                                    puntosA += 3
                                }
                            },
                            sign + "3",
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(0.75f)
                ) {
                    TeamBox(
                        stringResource(R.string.equipo) + " 1",
                        puntosA,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .weight(0.05f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "x",
                        fontSize = 40.sp,
                        color = Color.White,
                    )
                }
            }
            Row(
                modifier = Modifier.weight(0.3f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.weight(0.25f).fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier.weight(0.33f).fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        PlusButton(
                            Modifier
                                .clip(CircleShape)
                                .padding(4.dp, 0.dp),
                            {
                                if (isEdit) {
                                    puntosB -= 1
                                } else {
                                    puntosB += 1
                                }
                            },
                            sign + "1",
                        )
                    }
                    Row (
                        modifier = Modifier.weight(0.33f).fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        PlusButton(
                            Modifier
                                .clip(CircleShape)
                                .padding(4.dp, 0.dp),
                            {
                                if (isEdit) {
                                    puntosB -= 2
                                } else {
                                    puntosB += 2
                                }
                            },
                            sign + "2",
                        )
                    }
                    Row (
                        modifier = Modifier.weight(0.33f).fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        PlusButton(
                            Modifier
                                .clip(CircleShape)
                                .padding(4.dp, 0.dp),
                            {
                                if (isEdit) {
                                    puntosB -= 3
                                } else {
                                    puntosB += 3
                                }
                            },
                            sign + "3",
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(0.75f)
                ) {
                    TeamBox(
                        stringResource(R.string.equipo) + " 2",
                        puntosB,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.weight(0.2f).fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = stringResource(R.string.faltas),
                            color = Color.White,
                            fontSize = 25.sp,
                        )
                    }
                }
                Row(
                    modifier = Modifier.weight(0.4f).fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Button(
                            onClick = {},
                            modifier = Modifier.padding(16.dp, 0.dp),
                            colors = ButtonColors(
                                Color.White,
                                Color.Black,
                                Color.Black,
                                Color.White
                            ),
                        ) {
                            Text(text = "+")
                        }
                    }
                    Column {
                        Text(
                            text = "0",
                            fontSize = 30.sp,
                            color = Color.Yellow,
                            modifier = Modifier.padding(16.dp, 0.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "X",
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.padding(16.dp, 0.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "0",
                            fontSize = 30.sp,
                            color = Color.Yellow,
                            modifier = Modifier.padding(16.dp, 0.dp)
                        )
                    }
                    Column {
                        Button(
                            onClick = {},
                            colors = ButtonColors(
                                Color.White,
                                Color.Black,
                                Color.Black,
                                Color.White
                            ),
                        ) {
                            Text(text = "+")
                        }
                    }
                }
                Row(
                    modifier = Modifier.weight(0.4f).fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomButtons(
                        Modifier.padding(16.dp, 0.dp),
                        {
                        isEdit = !isEdit
                        if (sign == "+")
                            sign = "-"
                        else
                            sign = "+"
                    }, stringResource(R.string.editar))
                    BottomButtons(Modifier.padding(16.dp, 0.dp),{}, stringResource(R.string.compartir))
                }
            }
        }
    }
}

@Composable
fun BottomButtons(modifier: Modifier = Modifier, onClick: () -> Unit = {}, text: String = "") {
    Column {
        Button(
            onClick = onClick,
            colors = ButtonColors(Color.Blue, Color.White, Color.Blue, Color.White),
            modifier = modifier
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun PlusButton(modifier: Modifier = Modifier, onClick: () -> Unit, text: String = "0") {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonColors(Color.White, Color.Black, Color.Black, Color.White),
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
        )
    }
}

@Composable
fun TeamBox(teamName: String = R.string.equipo.toString(), score: Int = 0) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = teamName,
            modifier = Modifier.align(Alignment.TopCenter),
            fontSize = 40.sp,
            color = Color.White,
        )
        Text(
            text = score.toString(),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 90.sp,
            color = Color.Green,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreenPortrait(Modifier)
}

@Composable
fun MainScreenLandscape(modifier: Modifier = Modifier) {
    var puntosA by remember { mutableIntStateOf(34) }
    var puntosB by remember { mutableIntStateOf(18) }
    var isEdit by remember { mutableStateOf(false) }
    var sign by remember { mutableStateOf("+") }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Row(
            modifier = Modifier
                .padding(padding)
                .background(Color.Black)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PlusButton(
                    Modifier.clip(CircleShape).padding(4.dp),
                    { puntosA = if (isEdit) puntosA - 1 else puntosA + 1 },
                    sign + "1",
                )
                PlusButton(
                    Modifier.clip(CircleShape).padding(4.dp),
                    { puntosA = if (isEdit) puntosA - 2 else puntosA + 2 },
                    sign + "2",
                )
                PlusButton(
                    Modifier.clip(CircleShape).padding(4.dp),
                    { puntosA = if (isEdit) puntosA - 3 else puntosA + 3 },
                    sign + "3",
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.clip(CircleShape),
                        colors = ButtonColors(Color.Black, Color.White, Color.White, Color.Black),
                        border = BorderStroke(5.dp, Color.White),
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Play")
                    }
                    Text(
                        text = "10 : 00",
                        color = Color.Red,
                        fontSize = 45.sp,
                    )
                    Text(
                        text = "1º",
                        fontSize = 30.sp,
                        color = Color.White,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TeamBox(
                        R.string.equipo.toString() + " 1",
                        puntosA
                    )
                    Text(
                        text = "X",
                        fontSize = 40.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    TeamBox(
                        R.string.equipo.toString() + " 2",
                        puntosB
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonColors(Color.White, Color.Black, Color.Black, Color.White),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "+")
                    }
                    Text(
                        text = "0",
                        fontSize = 30.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "X",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "0",
                        fontSize = 30.sp,
                        color = Color.Yellow,
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(
                        onClick = {},
                        colors = ButtonColors(Color.White, Color.Black, Color.Black, Color.White),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "+")
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PlusButton(
                    Modifier.clip(CircleShape).padding(4.dp),
                    { puntosB = if (isEdit) puntosB - 1 else puntosB + 1 },
                    sign + "1",
                )
                PlusButton(
                    Modifier.clip(CircleShape).padding(4.dp),
                    { puntosB = if (isEdit) puntosB - 2 else puntosB + 2 },
                    sign + "2",
                )
                PlusButton(
                    Modifier.clip(CircleShape).padding(4.dp),
                    { puntosB = if (isEdit) puntosB - 3 else puntosB + 3 },
                    sign + "3",
                )
            }
        }
    }
}
