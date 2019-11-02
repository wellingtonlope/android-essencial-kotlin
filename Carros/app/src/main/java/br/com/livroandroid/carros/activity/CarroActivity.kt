package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.jetbrains.anko.*

class CarroActivity : AppCompatActivity() {
    val carro by lazy { intent.getParcelableExtra<Carro>("carro") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        // Seta o nome do carro como título da Toolbar
        setupToolbar(R.id.toolbar, carro?.nome, true)
        // Atualiza os dados do carro na tela
        initView()
    }

    private fun initView() {
        // Variáveis geradas automaticamente pelo kotlin extensions (veja import)
        tDesc.text = carro?.desc
        appBarImg.loadUrl(carro?.urlFoto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return true
    }

    // Trata os eventos do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_editar -> {
                startActivity<CarroFormActivity>("carro" to carro)
                finish()
            }
            R.id.action_deletar -> {
                alert(R.string.msg_confirma_excluir_carro, R.string.app_name) {
                    positiveButton(R.string.sim) {
                        taskExcluir()
                    }
                    negativeButton(R.string.nao){
                        // Não confirmou
                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        doAsync {
            val response = CarroService.delete(carro)
            uiThread {
                toast(response.msg)
                finish()
            }
        }
    }
}
