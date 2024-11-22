package com.example.myapplication.bancoDados

class GerenciaUsuario {
    // Lista para armazenar usuários cadastrados
    private val usuarios = mutableListOf<Usuario>()

    // Método para adicionar um novo usuário
    fun adicionarUsuario(nomeUsuario: String, senha: String): Boolean {
        // Verifica se o usuário já existe
        if (usuarios.any { it.nomeUsuario == nomeUsuario }) {
            return false // Usuário já existe
        }
        // Adiciona o novo usuário
        usuarios.add(Usuario(nomeUsuario, senha))
        return true // Sucesso
    }

    // Método para verificar se um usuário existe e se a senha está correta
    fun verificarUsuario(nomeUsuario: String, senha: String): Boolean {
        return usuarios.any { it.nomeUsuario == nomeUsuario && it.senha == senha }
    }

    // Método para verificar se um usuário existe sem verificar a senha
    fun usuarioExiste(nomeUsuario: String): Boolean {
        return usuarios.any { it.nomeUsuario == nomeUsuario }
    }
}
