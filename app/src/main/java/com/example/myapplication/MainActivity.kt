package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.myapplication.bancoDados.GerenciaUsuario
import com.example.myapplication.telas.CriarContaScreen
import com.example.myapplication.telas.HistoricoScreen
import com.example.myapplication.telas.LoginScreen
import com.example.myapplication.telas.MetasScreen
import com.example.myapplication.telas.SplashScreen


class MainActivity : ComponentActivity() {
    // Instância do gerenciador de usuários
    private val gerenciaUsuario = GerenciaUsuario()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(gerenciaUsuario) // Passando a instância do gerenciador de usuários
        }
    }
}

// Composable principal que gerencia as telas
@Composable
fun MyApp(gerenciaUsuario: GerenciaUsuario) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) } // Inicia na SplashScreen
    var username by remember { mutableStateOf("") } // Armazena o nome de usuário
    var password by remember { mutableStateOf("") } // Armazena a senha
    var errorMessage by remember { mutableStateOf("") } // Armazena a mensagem de erro

    when (currentScreen) {
        is Screen.Splash -> SplashScreen(onSplashFinished = { currentScreen = Screen.Metas }) // Muda para a tela inicial após a splash
        is Screen.Login -> LoginScreen(
            username = username,
            password = password,
            onUsernameChange = { username = it },
            onPasswordChange = { password = it },
            onLoginSuccess = {
                if (username.isBlank() || password.isBlank()) {
                    errorMessage = "Por favor, preencha todos os campos."
                } else if (!gerenciaUsuario.usuarioExiste(username)) {
                    errorMessage = "Usuário não existe."
                } else if (!gerenciaUsuario.verificarUsuario(username, password)) {
                    errorMessage = "Senha incorreta."
                } else {
                    currentScreen = Screen.Metas
                    errorMessage = ""
                }
            },
            onCreateAccount = { currentScreen = Screen.CriarConta },
            errorMessage = errorMessage
        )
        is Screen.Metas -> MetasScreen(
            onGoToHistorico = { currentScreen = Screen.Historico },
            onLogout = { currentScreen = Screen.Login }
        )
        is Screen.Historico -> HistoricoScreen(onBack = { currentScreen = Screen.Metas })
        is Screen.CriarConta -> CriarContaScreen(
            gerenciaUsuario = gerenciaUsuario,
            onBack = { currentScreen = Screen.Login }
        )
    }
}


// Definição da classe Screen para controle de navegação
sealed class Screen {
    object Splash : Screen() // Tela de splash
    object Login : Screen()
    object Metas : Screen()
    object Historico : Screen()
    object CriarConta : Screen()
}

