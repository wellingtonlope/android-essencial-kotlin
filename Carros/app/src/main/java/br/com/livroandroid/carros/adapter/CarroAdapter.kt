package br.com.livroandroid.carros.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.extensions.loadUrl
import kotlinx.android.synthetic.main.adapter_carro.view.*

// define o construtor que recebe (carros, onclick)
class CarroAdapter(
    val carros: List<Carro>,
    val onClick: (Carro) -> Unit
) :
    RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {
    // ViewHolder com as views
    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun getItemCount() = this.carros.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrosViewHolder {
        // Infla a view do adapter
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_carro, parent, false)
        // Retorna o ViewHolder que contém todas as views
        val holder = CarrosViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        // Recupera o objeto carro
        val carro = carros[position]
        // Atualiza os daos do carro
        val view = holder.itemView
        view.tNome.text = carro.nome
        // Faz o download da foto e mostra o ProgressBar
        view.img.loadUrl(carro.urlFoto, view.progress)
        // Adiciona o evento de clique na linha
        holder.itemView.setOnClickListener { onClick(carro) }
    }
}
