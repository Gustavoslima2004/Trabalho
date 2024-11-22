package com.example.myapplication.telas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginSuccess: () -> Unit,
    onCreateAccount: () -> Unit,
    errorMessage: String // Adicionado parâmetro para mensagem de erro
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text("Nome de Usuário") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Senha") },
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
                if (username.isNotBlank() && password.isNotBlank()) {
                    onLoginSuccess() // Chama a função de sucesso de login
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hiperlink para criar conta
        Text(
            text = "Criar Conta",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable { onCreateAccount() }
                .padding(8.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
                textDecoration = TextDecoration.None
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        username = "",
        password = "",
        onUsernameChange = {},
        onPasswordChange = {},
        onLoginSuccess = {},
        onCreateAccount = {},
        errorMessage = "" // Mensagem de erro vazia para o preview
    )
}
