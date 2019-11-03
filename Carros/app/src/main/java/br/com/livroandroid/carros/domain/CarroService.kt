package br.com.livroandroid.carros.domain

import android.util.Log
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.dao.DatabaseManager
import br.com.livroandroid.carros.domain.retrofit.CarrosREST
import br.com.livroandroid.carros.extensions.getText
import br.com.livroandroid.carros.extensions.getXml
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CarroService {
    private val TAG = "livro"
    private val BASE_URL = "http://livrowebservices.com.br/rest/carros/"
    private var service: CarrosREST

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(CarrosREST::class.java)
    }

    // Busca os carros por tipo (clássicos, esportivos ou luxo)
    fun getCarros(tipo: TipoCarro): List<Carro> {
        val call = service.getCarros(tipo.name)
        return call.execute().body()
    }

    // Salva um carro
    fun save(carro: Carro): Response {
        val call = service.save(carro)
        return call.execute().body()
    }

    // Deleta um carro
    fun delete(carro: Carro): Response {
        val call = service.delete(carro.id)
        val response = call.execute().body()
        if (response.isOk()) {
            // se removeu do servidor, remove dos favoritos
            val dao = DatabaseManager.getCarroDAO()
            dao.delete(carro)
        }
        return response
    }

    // Retorna o arquivo que temos que ler para o tipo informado
    fun getArquivoRaw(tipo: TipoCarro) = when (tipo) {
        TipoCarro.classicos -> R.raw.carros_classicos
        TipoCarro.esportivos -> R.raw.carros_esportivos
        else -> R.raw.carros_luxo
    }

    // Lê o XML e cria a lista de carros
    fun parseXML(xmlString: String?): List<Carro> {
        val carros = mutableListOf<Carro>()
        val xml = xmlString?.getXml()
        // Lê todas as tags <carro>
        val nodeCarros = xml?.getChildren("carro")
        // Insere cada carro na lista
        if (nodeCarros != null) {
            for (node in nodeCarros) {
                val c = Carro()
                // Lê as informações de cada carro
                c.nome = node.getText("nome")
                c.desc = node.getText("desc")
                c.urlFoto = node.getText("url_foto")
                carros.add(c)
            }
        }
        Log.d(TAG, "${carros.size} carros encontrados.")
        return carros
    }

    // Lê o XML e cria a lista de carros
    fun parseJson(json: String?): List<Carro> {
        val carros = mutableListOf<Carro>()
        // Criar um array com esse JSON
        val array = JSONArray(json)
        // Percorre cada carro (JSON)

        for (i in 0 until array.length()) {
            // JSON do carro
            val jsonCarro = array.getJSONObject(i)
            val c = Carro()
            // Lê as informações de cada carro
            c.nome = jsonCarro.optString("nome")
            c.desc = jsonCarro.optString("desc")
            c.urlFoto = jsonCarro.optString("url_foto")
            carros.add(c)
        }

        Log.d(TAG, "${carros.size} carros encontrados.")
        return carros
    }
}
