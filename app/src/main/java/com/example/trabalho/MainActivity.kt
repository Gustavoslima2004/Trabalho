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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp()
        }
    }
}

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

            // Texto "Metas do dia"
            Text(
                text = "Metas do dia",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

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

            // Texto e campo de entrada para o horário 1
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    value = hour1,
                    onValueChange = { hour1 = it },
                    modifier = Modifier
                        .width(60.dp)
                        .background(Color.Black, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(50.dp)),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "h - am",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            // Texto e campo de entrada para o horário 2
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    value = hour2,
                    onValueChange = { hour2 = it },
                    modifier = Modifier
                        .width(60.dp)
                        .background(Color.Black, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(50.dp)),
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "h - pm",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = text1,
                            onValueChange = { text1 = it },
                            label = { Text("Enter text 1") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked1,
                            onCheckedChange = { isChecked1 = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Segundo conjunto
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = text2,
                            onValueChange = { text2 = it },
                            label = { Text("Enter text 2") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked2,
                            onCheckedChange = { isChecked2 = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Terceiro conjunto
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = text3,
                            onValueChange = { text3 = it },
                            label = { Text("Enter text 3") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked3,
                            onCheckedChange = { isChecked3 = it }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quarto conjunto
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = text4,
                            onValueChange = { text4 = it },
                            label = { Text("Enter text 4") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked4,
                            onCheckedChange = { isChecked4 = it }
                        )
                    }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}