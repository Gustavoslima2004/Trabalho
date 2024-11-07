package com.example.myapplication.telas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import com.example.myapplication.bancoDados.GerenciaUsuario

@Composable
fun CriarContaScreen(onBack: () -> Unit, gerenciaUsuario: GerenciaUsuario) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") } // Estado para mensagem de erro

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nome de Usuário") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Exibir mensagem de erro, se existir
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = {
                if (password.text == confirmPassword.text) {
                    // Adicionar o usuário usando o GerenciaUsuario
                    if (gerenciaUsuario.adicionarUsuario(username.text, password.text)) {
                        // Lógica para lidar com sucesso na criação da conta
                        onBack() // Voltar para a tela de login
                    } else {
                        // Exibir mensagem de erro: usuário já existe
                        errorMessage = "Usuário já existe!"
                    }
                } else {
                    // Exibir mensagem de erro: senhas não coincidem
                    errorMessage = "As senhas não coincidem!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Criar Conta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para voltar ao login
        Button(
            onClick = { onBack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
