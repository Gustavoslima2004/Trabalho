package com.example.myapplication.telas

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(true) {
        // Animação de escala
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000) // Duração de 1 segundo
        )
        delay(1000) // Manter na tela por 1 segundos
        onSplashFinished() // Finaliza a splash e navega para a próxima tela
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colorStops = arrayOf(
                        0.2f to Color(0xFF001F54), // Azul escuro no início (0%)
                        0.5f to Color(0xFF1C2023), // Azul escuro até 70%
                        1.0f to Color(0xFFD1A865)  // Amarelo queimado no final (100%)
                    ),
                    start = Offset(0f, 0f), // Começa no lado esquerdo
                    end = Offset(Float.POSITIVE_INFINITY, 0f) // Termina no lado direito
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Substitua pelo nome do recurso de sua logo
            contentDescription = "Logo do App",
            modifier = Modifier
                .size(600.dp) // Tamanho da logo
                .scale(scale.value) // Animação de escala
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(onSplashFinished = {})
}
