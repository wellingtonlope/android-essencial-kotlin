package br.com.livroandroid.carros.utils

import android.content.SharedPreferences
import br.com.livroandroid.carros.CarrosApplication

object Prefs {
    private val PREF_ID = "carros"
    private fun prefs(): SharedPreferences {
        val context = CarrosApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setInt(flag: String, valor: Int): Unit = prefs().edit().putInt(flag, valor).apply()

    fun getInt(flag: String): Int = prefs().getInt(flag, 0)

    fun setString(flag: String, valor: String): Unit = prefs().edit().putString(flag, valor).apply()

    fun getString(flag: String): String? = prefs().getString(flag, "")
}