package com.example.myapplication.telas

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.*

fun getMonthName(month: Int): String {
    val months = arrayOf(
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    )
    return months[month]
}

fun getDaysInMonth(year: Int, month: Int): List<Int> {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, 1)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    return (1..daysInMonth).toList()
}

@Composable
fun HistoricoScreen(onBack: () -> Unit) {
    val calendar = Calendar.getInstance()
    var selectedYear by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var selectedMonth by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var selectedDay by remember { mutableStateOf(-1) } // -1 para indicar que nenhum dia está selecionado
    var selectedDate by remember { mutableStateOf("") }  // Formato "dd/MM/yyyy"

    // Acesso ao contexto para usar SharedPreferences
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    // Função para definir a data de hoje
    fun setToday() {
        val today = Calendar.getInstance()
        selectedYear = today.get(Calendar.YEAR)
        selectedMonth = today.get(Calendar.MONTH)
        selectedDay = today.get(Calendar.DAY_OF_MONTH)
        selectedDate = "${selectedDay.toString().padStart(2, '0')}/${(selectedMonth + 1).toString().padStart(2, '0')}/$selectedYear"
    }

    // Column principal para centralizar a Box e o botão
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(500.dp)
                .height(500.dp)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Escolha o dia:",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "<",
                        fontSize = 40.sp,
                        color = Color.White,
                        modifier = Modifier.clickable {
                            selectedMonth = (selectedMonth - 1 + 12) % 12
                            if (selectedMonth == 11) selectedYear -= 1
                        }
                    )
                    Text(
                        text = "${getMonthName(selectedMonth)} $selectedYear",
                        fontSize = 28.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = ">",
                        fontSize = 40.sp,
                        color = Color.White,
                        modifier = Modifier.clickable {
                            selectedMonth = (selectedMonth + 1) % 12
                            if (selectedMonth == 0) selectedYear += 1
                        }
                    )
                }

                val daysInMonth = getDaysInMonth(selectedYear, selectedMonth)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    contentPadding = PaddingValues(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(daysInMonth.size) { index ->
                        DayItem(
                            day = daysInMonth[index],
                            isSelected = daysInMonth[index] == selectedDay,
                            onClick = {
                                selectedDay = daysInMonth[index]
                                selectedDate = "${daysInMonth[index].toString().padStart(2, '0')}/${(selectedMonth + 1).toString().padStart(2, '0')}/$selectedYear"
                            }
                        )
                    }
                }

                // Adicionar o botão "Hoje" ao lado do último dia
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        setToday()  // Define a data de hoje
                    },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Hoje", fontSize = 20.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Salvar a data no SharedPreferences quando o botão "Abrir" for clicado
                if (selectedDate.isNotEmpty()) {
                    sharedPreferences.edit().apply {
                        putString("data_selecionada", selectedDate)
                        apply()
                    }
                }
                onBack()  // Voltar para a tela "Metas"
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 8.dp)
        ) {
            Text("Abrir", fontSize = 20.sp)
        }

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 8.dp)
        ) {
            Text("Voltar para Metas", fontSize = 20.sp)
        }
    }
}

@Composable
fun DayItem(day: Int, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(4.dp)
            .background(
                if (isSelected) Color.Blue else Color.LightGray,
                shape = MaterialTheme.shapes.small
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
