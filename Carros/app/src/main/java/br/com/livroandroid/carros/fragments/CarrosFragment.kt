package br.com.livroandroid.carros.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.activity.CarroActivity
import br.com.livroandroid.carros.adapter.CarroAdapter
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.domain.event.SaveCarroEvent
import br.com.livroandroid.carros.utils.AndroidUtils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_carros.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

open class CarrosFragment : BaseFragment() {
    private var tipo: TipoCarro = TipoCarro.classicos
    protected var carros = listOf<Carro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            // Lê o parâmetro tipo enviado (clássicos, esportivos ou luxo)
            tipo = arguments?.getSerializable("tipo") as TipoCarro
        }
        // Registra os eventos do bus
        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun onRefresh(event: SaveCarroEvent) {
        // Recebe o evento do bus
        taskCarros()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancela os eventos do bus
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carros, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Views
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        taskCarros()
    }

    open fun taskCarros() {
        val internetOk = AndroidUtils.isNetworkAvailable(context)
        if (internetOk) {
            // Abre uma thread
            doAsync {
                // Busca os carros
                carros = CarroService.getCarros(tipo)
                // Atualiza a lista na UI Thread
                uiThread {
                    recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }
                }
            }
        } else {
            val snack = Snackbar.make(
                activity?.findViewById(android.R.id.content)!!,
                "Sem conexão com a internet",
                Snackbar.LENGTH_LONG
            )
            snack.show()
        }
    }

    open fun onClickCarro(carro: Carro) {
        activity?.startActivity<CarroActivity>("carro" to carro)
    }

}
