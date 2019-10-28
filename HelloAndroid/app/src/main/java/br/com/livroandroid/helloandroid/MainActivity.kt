package br.com.livroandroid.helloandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "livro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Log.v(TAG, "log de verbose")
//        Log.d(TAG, "log de debug")
//        Log.i(TAG, "log de info")
//        Log.w(TAG, "log de alerta")
//        Log.e(TAG, "log de erro", RuntimeException("teste de erro"))

        onClick(R.id.btLogin) { onClickLogin() }
    }

    private fun onClickLogin() {
        // Lê os textos digitados com a extension
        val login = getTextString(R.id.tLogin)
        val senha = getTextString(R.id.tSenha)
        if ("ricardo" == login && "123" == senha) {
            toast("Bem-vindo, login realizado com sucesso.")
        } else {
            toast("Login e senha incorretos.")
        }
    }
}
