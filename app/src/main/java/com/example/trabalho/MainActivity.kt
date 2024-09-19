package com.example.trabalho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

//gustavo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var text1 by remember { mutableStateOf(TextFieldValue("")) }
    var isChecked1 by remember { mutableStateOf(false) }

    var text2 by remember { mutableStateOf(TextFieldValue("")) }
    var isChecked2 by remember { mutableStateOf(false) }

    var text3 by remember { mutableStateOf(TextFieldValue("")) }
    var isChecked3 by remember { mutableStateOf(false) }

    var text4 by remember { mutableStateOf(TextFieldValue("")) }
    var isChecked4 by remember { mutableStateOf(false) }

    var hour1 by remember { mutableStateOf(TextFieldValue("7")) }
    var hour2 by remember { mutableStateOf(TextFieldValue("7")) }
    var isDay by remember { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val backgroundImage = if (isDay) {
            R.drawable.background1
        } else {
            R.drawable.background2
        }

        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Snackbar host to display messages
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    val backgroundColor = when (snackbarData.visuals.message) {
                        "Metas definidas com sucesso!" -> Color.Green
                        "Horário inválido: deve estar entre 0h e 12h." -> Color.Yellow
                        else -> Color.Red
                    }
                    Snackbar(
                        snackbarData = snackbarData,
                        modifier = Modifier.background(backgroundColor),
                        containerColor = backgroundColor,
                        contentColor = Color.Black
                    )
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = Color.Black)
            ) {
                Text(
                    text = "Metas do dia",
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }

            // Botões "Dia" e "Noite"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { isDay = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isDay) Color.Gray else Color.Black
                    )
                ) {
                    Text("Dia")
                }
                Button(
                    onClick = { isDay = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isDay) Color.Gray else Color.Black
                    )
                ) {
                    Text("Noite")
                }
            }

            val corTexto = if (isDay) Color.Black else Color.White

            // Texto e campo de entrada para o horário 1
            TimeInputRow(
                hour = hour1,
                onHourChange = { hour1 = it },
                label = "h - am",
                corTexto = corTexto
            )

            // Texto e campo de entrada para o horário 2
            TimeInputRow(
                hour = hour2,
                onHourChange = { hour2 = it },
                label = "h - am",
                corTexto = corTexto
            )

            // Box cinza
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(300.dp)
                    .background(Color.Gray)
                    .padding(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Primeiro conjunto
                    FieldRow(
                        text = text1,
                        onTextChange = { text1 = it },
                        isChecked = isChecked1,
                        onCheckedChange = {
                            isChecked1 = it
                            if (it) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Objetivo 1 finalizado")
                                }
                            }
                        },
                        label = "Objetivo - 1"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Segundo conjunto
                    FieldRow(
                        text = text2,
                        onTextChange = { text2 = it },
                        isChecked = isChecked2,
                        onCheckedChange = {
                            isChecked2 = it
                            if (it) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Objetivo 2 finalizado")
                                }
                            }
                        },
                        label = "Objetivo - 2"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Terceiro conjunto
                    FieldRow(
                        text = text3,
                        onTextChange = { text3 = it },
                        isChecked = isChecked3,
                        onCheckedChange = {
                            isChecked3 = it
                            if (it) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Objetivo 3 finalizado")
                                }
                            }
                        },
                        label = "Objetivo - 3"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quarto conjunto
                    FieldRow(
                        text = text4,
                        onTextChange = { text4 = it },
                        isChecked = isChecked4,
                        onCheckedChange = {
                            isChecked4 = it
                            if (it) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Objetivo 4 finalizado")
                                }
                            }
                        },
                        label = "Objetivo - 4"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de verificação
            Button(
                onClick = {
                    val hour1Int = hour1.text.toIntOrNull()
                    val hour2Int = hour2.text.toIntOrNull()

                    when {
                        text1.text.isEmpty() || text2.text.isEmpty() || text3.text.isEmpty() || text4.text.isEmpty() -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Operação inválida: Todos os campos devem ser preenchidos.")
                            }
                        }

                        hour1Int == null || hour2Int == null || hour1Int !in 0..12 || hour2Int !in 0..12 -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Horário inválido: deve estar entre 0h e 12h.")
                            }
                        }

                        else -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Metas definidas com sucesso!")
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verificar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeInputRow(hour: TextFieldValue, onHourChange: (TextFieldValue) -> Unit, label: String, corTexto: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = hour,
            onValueChange = { newValue ->
                if (newValue.text.length <= 2) {
                    onHourChange(newValue)
                }
            },
            modifier = Modifier
                .width(60.dp)
                .clip(RoundedCornerShape(50.dp)),
            textStyle = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            color = corTexto,
            fontSize = 24.sp
        )
    }
}

@Composable
fun FieldRow(
    text: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text(label) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
