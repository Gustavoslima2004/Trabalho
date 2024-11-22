package com.example.myapplication.bancoDados

object BancoDeUsuarios {
    private val usuarios = mutableListOf<Usuario>()

    // Função para adicionar um usuário ao "banco de dados"
    fun adicionarUsuario(usuario: Usuario): Boolean {
        return if (usuarios.any { it.nomeUsuario == usuario.nomeUsuario }) {
            false // Usuário já existe
        } else {
            usuarios.add(usuario)
            true
        }
    }

    // Função para verificar login
    fun verificarLogin(nomeUsuario: String, senha: String): Boolean {
        return usuarios.any { it.nomeUsuario == nomeUsuario && it.senha == senha }
    }
}
