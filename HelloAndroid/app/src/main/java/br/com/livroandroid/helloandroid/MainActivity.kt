package br.com.livroandroid.helloandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle

class MainActivity : DebugActivity() {
    //    private val TAG = "livro"
    private val context: Context get() = this

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
            val intent = Intent(context, BemVindoActivity::class.java)
            intent.putExtra("nome", "Ricardo Lecheta")
            startActivity(intent)
        } else {
            toast("Login e senha incorretos.")
        }
    }
}
