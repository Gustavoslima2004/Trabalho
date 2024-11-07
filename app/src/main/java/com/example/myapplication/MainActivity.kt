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



    //
    //
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Historico)
    /*Altere Login para Metas para deixar a tela inicial como o app de metas*/ }
    //
    //

    var username by remember { mutableStateOf("") } // Armazena o nome de usuário
    var password by remember { mutableStateOf("") } // Armazena a senha
    var errorMessage by remember { mutableStateOf("") } // Armazena a mensagem de erro

    when (currentScreen) {
        is Screen.Login -> LoginScreen(
            username = username,
            password = password,
            onUsernameChange = { username = it },
            onPasswordChange = { password = it },
            onLoginSuccess = {
                // Verifica se o usuário existe
                if (username.isBlank() || password.isBlank()) {
                    errorMessage = "Por favor, preencha todos os campos."
                } else if (!gerenciaUsuario.usuarioExiste(username)) {
                    errorMessage = "Usuário não existe."
                } else if (!gerenciaUsuario.verificarUsuario(username, password)) {
                    errorMessage = "Senha incorreta."
                } else {
                    currentScreen = Screen.Metas
                    errorMessage = "" // Limpa a mensagem de erro
                }
            },
            onCreateAccount = { currentScreen = Screen.CriarConta },
            errorMessage = errorMessage // Passa a mensagem de erro para a tela de login
        )
        is Screen.Metas -> MetasScreen(
            onGoToHistorico = { currentScreen = Screen.Historico },
            onLogout = { currentScreen = Screen.Login }
        )
        is Screen.Historico -> HistoricoScreen(onBack = { currentScreen = Screen.Metas })
        is Screen.CriarConta -> CriarContaScreen(
            gerenciaUsuario = gerenciaUsuario, // Passando gerenciaUsuario para a tela de criação de conta
            onBack = { currentScreen = Screen.Login }
        )
    }
}

// Definição da classe Screen para controle de navegação
sealed class Screen {
    object Login : Screen()
    object Metas : Screen()
    object Historico : Screen()
    object CriarConta : Screen() // Nova tela para criação de conta
}
