package br.com.livroandroid.carros.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : AppCompatActivity() {
    var carro: Carro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        //Lê o carro enviado como parâmetro
        carro = intent.getSerializableExtra("carro") as Carro
        // Seta o nome do carro como título da Toolbar
        setupToolbar(R.id.toolbar, carro?.nome, true)
        // Atualiza os dados do carro na tela
        initView()
    }

    private fun initView() {
        // Variáveis geradas automaticamente pelo kotlin extensions (veja import)
        tDesc.text = carro?.desc
        img.loadUrl(carro?.urlFoto)
    }
}
