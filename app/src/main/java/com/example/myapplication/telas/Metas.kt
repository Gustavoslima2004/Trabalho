package com.example.myapplication.telas

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.launch
import com.example.myapplication.bancoDados.HelperDatas
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat

import java.util.*

@Composable
fun MetasScreen(onGoToHistorico: () -> Unit, onLogout: () -> Unit) {


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

    var isMetasDefinidas by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val backgroundImage = if (isDay) {
            R.drawable.background1
        } else {
            R.drawable.background2
        }

        Crossfade(targetState = backgroundImage) { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween, // Permite que os elementos ocupem o espaço restante
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Snackbar host to display messages
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    val backgroundColor = when (snackbarData.visuals.message) {
                        "Objetivo: 1 Concluído!" -> Color(0xCD3DE211)
                        "Objetivo: 2 Concluído!" -> Color(0xCD3DE211)
                        "Objetivo: 3 Concluído!" -> Color(0xCD3DE211)
                        "Objetivo: 4 Concluído!" -> Color(0xCD3DE211)
                        "Metas definidas com sucesso!" -> Color(0xCD3DE211)
                        "Horário inválido: deve estar entre 00h e 23h." -> Color(0xEBFFDD00)
                        else -> Color(0xB4CE0505)
                    }
                    Snackbar(
                        snackbarData = snackbarData,
                        modifier = Modifier.background(backgroundColor),
                        containerColor = backgroundColor,
                        contentColor = Color.White
                    )
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )




                    Box(
                        modifier = Modifier
                            .width(350.dp) // Largura da Box
                            .height(60.dp) // Altura da Box
                            .background(Color.Gray, shape = RoundedCornerShape(10.dp)) // Box com borda arredondada
                            .padding(16.dp), // Padding interno para espaçamento
                        contentAlignment = Alignment.Center // Centraliza o conteúdo dentro da Box
                    ) {
                        // Obter o contexto para usar a função do HelperDatas
                        val context = LocalContext.current
                        val savedDate = HelperDatas.getSavedDate(context)

                        // Se a data salva não estiver disponível, usamos a data atual
                        val calendar = if (savedDate.isNotEmpty()) {
                            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            val date = sdf.parse(savedDate) ?: Calendar.getInstance().time
                            Calendar.getInstance().apply { time = date }
                        } else {
                            Calendar.getInstance() // Data atual
                        }

                        // Exibir a data formatada na Box
                        Text(
                            text = String.format(
                                Locale.getDefault(), // Usando o Locale do dispositivo para formatação
                                "%02d/%02d/%d",
                                calendar.get(Calendar.DAY_OF_MONTH),
                                calendar.get(Calendar.MONTH) + 1, // Mês ajustado para o padrão 1-12
                                calendar.get(Calendar.YEAR)
                            ),
                            color = Color.White, // Cor do texto
                            fontSize = 20.sp, // Tamanho do texto
                            fontWeight = FontWeight.Bold // Negrito para destacar a data
                        )
                    }

            Spacer(modifier = Modifier.height(10.dp))




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
                        containerColor = if (isDay) Color(0xFF0A98D6) else Color(0xFFFFE91A)
                    )
                ) {
                    Text("Dia")
                }
                Button(
                    onClick = { isDay = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!isDay) Color(0xF30A98D6) else Color(0xFF1E2329)
                    )
                ) {
                    Text("Noite")
                }
            }
            val texto = if (isDay) {
                Color.Black
            }else{
                Color.White
            }

            val day = if (isDay) "am" else "pm"
            val night = if (isDay) "pm" else "am"



            // Texto e campo de entrada para o horário 1

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(60.dp) // Largura definida para o campo de texto
                        .height(60.dp) // Definindo altura para manter proporção
                        .background(Color.LightGray, shape = RoundedCornerShape(100.dp))
                        .clip(RoundedCornerShape(50.dp)),
                    contentAlignment = Alignment.Center // Centraliza o conteúdo
                ) {
                    BasicTextField(
                        value = hour1.text.padStart(2, '0'), // Garante que tenha dois dígitos
                        onValueChange = {
                            val formatted = it.filter { char -> char.isDigit() }.take(2) // Limita a dois dígitos numéricos
                            hour1 = TextFieldValue(formatted)
                        },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 24.sp,
                            color = Color.Black, // Ajuste de cor
                            textAlign = TextAlign.Center // Centraliza o texto dentro do campo
                        ),
                        singleLine = true, // Permite apenas uma linha
                        modifier = Modifier.fillMaxWidth() // Preenche o Box para centralização
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "h - $day" ,
                    color = texto,
                    fontSize = 24.sp
                )
            }



// Para o segundo horário
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(60.dp) // Largura definida para o campo de texto
                        .height(60.dp) // Definindo altura para manter proporção
                        .background(Color.LightGray, shape = RoundedCornerShape(100.dp))
                        .clip(RoundedCornerShape(50.dp)),
                    contentAlignment = Alignment.Center // Centraliza o conteúdo
                ) {
                    BasicTextField(
                        value = hour2.text.padStart(2, '0'), // Garante que tenha dois dígitos
                        onValueChange = {
                            val formatted = it.filter { char -> char.isDigit() }.take(2) // Limita a dois dígitos numéricos
                            hour2 = TextFieldValue(formatted)
                        },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 24.sp,
                            color = Color.Black, // Ajuste de cor
                            textAlign = TextAlign.Center // Centraliza o texto dentro do campo
                        ),
                        singleLine = true, // Permite apenas uma linha
                        modifier = Modifier.fillMaxWidth() // Preenche o Box para centralização
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "h - $night",
                    color = texto,
                    fontSize = 24.sp
                )
            }



            // Box cinza
            Box(
                modifier = Modifier
                    .width(350.dp)
                    .height(300.dp)
                    .background(Color.DarkGray)
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
                            label = { Text("Objetivo: 1") },
                            modifier = Modifier.weight(1f)
                                .background(Color.Black)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked1,
                            onCheckedChange = {
                                if (isMetasDefinidas) { // Permite ativar o switch somente se as metas foram definidas
                                    isChecked1 = it
                                    if (it) {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Objetivo: 1 Concluído!")
                                        }
                                    }
                                }
                            }
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
                            label = { Text("Objetivo: 2") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked2,
                            onCheckedChange = {
                                if (isMetasDefinidas) { // Permite ativar o switch somente se as metas foram definidas
                                    isChecked2 = it
                                    if (it) {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Objetivo: 2 Concluído!")
                                        }
                                    }
                                }
                            }
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
                            label = { Text("Objetivo: 3") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked3,
                            onCheckedChange = {
                                if (isMetasDefinidas) { // Permite ativar o switch somente se as metas foram definidas
                                    isChecked3 = it
                                    if (it) {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Objetivo: 3 Concluído!")
                                        }
                                    }
                                }
                            }
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
                            label = { Text("Objetivo: 4") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Switch(
                            checked = isChecked4,
                            onCheckedChange = {
                                if (isMetasDefinidas) { // Permite ativar o switch somente se as metas foram definidas
                                    isChecked4 = it
                                    if (it) {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Objetivo: 4 Concluído!")
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de verificação de metas
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

                        hour1Int == null || hour2Int == null || hour1Int !in 0..23 || hour2Int !in 0..23 -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Horário inválido: deve estar entre 00h e 23h.")
                            }
                        }

                        else -> {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Metas definidas com sucesso!")
                                isMetasDefinidas = true
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03A9F4),
                    contentColor = Color.White
                )
            ) {
                Text("Definir Metas")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp), // Pode ajustar o padding conforme necessário
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onGoToHistorico) {
                    Text("Ver Histórico")
                }
                Button(onClick = onLogout) {
                    Text("Logout")
                }
            }
        }
    }
}
