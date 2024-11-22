package com.example.myapplication.bancoDados

import android.content.Context

object HelperDatas {

    // Função para obter a data salva na SharedPreferences
    fun getSavedDate(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getString("data_selecionada", "") ?: ""
    }
}
