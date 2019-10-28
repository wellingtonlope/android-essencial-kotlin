package br.com.livroandroid.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView

class BemVindoActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Views
        setContentView(R.layout.activity_bem_vindo)
        val textView = findViewById<TextView>(R.id.text)
        // Args
        val nome = intent.getStringExtra("nome")
        textView.text = "$nome, seja bem vindo."
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
            // Treata o clique no botão de voltar
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
