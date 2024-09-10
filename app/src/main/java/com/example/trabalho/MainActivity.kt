package com.example.trabalho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texto "Metas do dia"
        Text(
            text = "Metas do dia",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp) // Espaço abaixo do texto
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

        // Texto e campo de entrada para o horário
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
                text = "h - pm",
                color = Color.White,
                fontSize = 24.sp
            )
        }

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
                text = "h - am",
                color = Color.White,
                fontSize = 24.sp
            )
        }

        // Box cinza
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(300.dp) // Ajuste a altura para acomodar todos os conjuntos
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

                Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os conjuntos

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

                Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os conjuntos

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

                Spacer(modifier = Modifier.height(16.dp)) // Espaço entre os conjuntos

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

        // Espaço entre a Box cinza e o botão
        Spacer(modifier = Modifier.height(16.dp))

        // Botão
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
