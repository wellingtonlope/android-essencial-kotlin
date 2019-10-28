package br.com.livroandroid.carros.activity

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    // Propriedade para acessar o contexto de qualquer lugar
    protected val context: Context get() = this
}