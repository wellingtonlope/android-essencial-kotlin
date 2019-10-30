package br.com.livroandroid.carros.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import com.squareup.picasso.Picasso

// define o construtor que recebe (carros, onclick)
class CarroAdapter(
    val carros: List<Carro>,
    val onClick: (Carro) -> Unit
) :
    RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {
    // ViewHolder com as views
    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tNome: TextView
        var img: ImageView
        var progress: ProgressBar
        var cardView: CardView

        init {
            // Salvar as views no ViewHolder
            tNome = view.findViewById(R.id.tNome)
            img = view.findViewById(R.id.img)
            progress = view.findViewById(R.id.progress)
            cardView = view.findViewById(R.id.cardView)
        }
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
        val context = holder.itemView.context
        // Recupera o objeto carro
        val carro = carros[position]
        // Atualiza os daos do carro
        holder.tNome.text = carro.nome
        holder.progress.visibility = View.VISIBLE
        // Faz o download da foto e mostra o ProgressBar
        Picasso.with(context).load(carro.urlFoto).fit().into(holder.img,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    holder.progress.visibility = View.GONE
                }

                override fun onError() {
                    holder.progress.visibility = View.GONE
                }
            })
        // Adiciona o evento de clique na linha
        holder.itemView.setOnClickListener { onClick(carro) }
    }
}
