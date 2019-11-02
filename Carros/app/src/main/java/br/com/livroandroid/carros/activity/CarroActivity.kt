package br.com.livroandroid.carros.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro_contents.*

class CarroActivity : AppCompatActivity() {
    val carro by lazy { intent.getParcelableExtra<Carro>("carro")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        // Seta o nome do carro como título da Toolbar
        setupToolbar(R.id.toolbar, carro.nome, true)
        // Atualiza os dados do carro na tela
        initView()
    }

    private fun initView() {
        // Variáveis geradas automaticamente pelo kotlin extensions (veja import)
        tDesc.text = carro.desc
        img.loadUrl(carro.urlFoto)
    }
}
