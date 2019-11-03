package br.com.livroandroid.carros.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.FavoritosService
import br.com.livroandroid.carros.domain.event.FavoritoEvent
import br.com.livroandroid.carros.domain.event.SaveCarroEvent
import br.com.livroandroid.carros.extensions.addFragment
import br.com.livroandroid.carros.extensions.loadUrl
import br.com.livroandroid.carros.extensions.setupToolbar
import br.com.livroandroid.carros.fragments.MapaFragment
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.*

class CarroActivity : BaseActivity() {
    val carro by lazy { intent.getParcelableExtra<Carro>("carro") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        // Seta o nome do carro como título da Toolbar
        setupToolbar(R.id.toolbar, carro?.nome, true)
        // Atualiza os dados do carro na tela
        initView()
        // Variável gerada automaticamnte pelo kotlin extensions
        fab.setOnClickListener { onClickFavoritar(carro) }
    }

    private fun onClickFavoritar(carro: Carro) {
        doAsync {
            val favoritado = FavoritosService.favoritar(carro)
            uiThread {
                // Alerta de sucesso
                setFavoriteColor(favoritado)
                toast(if (favoritado) R.string.msg_carro_favoritado else R.string.msg_carro_desfavoritado)
                // Dispara um evento para atualizar a lista de favoritos
                EventBus.getDefault().post(FavoritoEvent(carro))
            }
        }
    }

    // Desenha a cor do FAB conforme está favoritado ou não
    fun setFavoriteColor(favorito: Boolean) {
        // Troca a cor conforme o status do favorito
        val fundo = ContextCompat.getColor(
            this,
            if (favorito) R.color.favorito_on else R.color.favorito_off
        )
        val cor =
            ContextCompat.getColor(this, if (favorito) R.color.yellow else R.color.favorito_on)
        fab.backgroundTintList = ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(fundo))
        fab.setColorFilter(cor)
    }

    private fun initView() {
        // Variáveis geradas automaticamente pelo kotlin extensions (veja import)
        tDesc.text = carro?.desc
        appBarImg.loadUrl(carro?.urlFoto)
        // Foto do carro(pequena com transparência)
        img.loadUrl(carro?.urlFoto)
        // Troca o Vídeo
        imgPlayVideo.setOnClickListener {
            val url = carro?.urlVideo
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "video/*")
            startActivity(intent)
        }

        // Cor botão favoritar
        doAsync {
            val favorito = FavoritosService.isFavorito(carro)
            uiThread {
                setFavoriteColor(favorito)
            }
        }

        // Adiciona o fragment do mapa
        val mapaFragment = MapaFragment()
        mapaFragment.arguments = intent.extras
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mapaFragment, mapaFragment)
            .commit()
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
                    negativeButton(R.string.nao) {
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
                // Dispara um evento para atualizar a lista de carros
                EventBus.getDefault().post(SaveCarroEvent(carro))
            }
        }
    }
}
