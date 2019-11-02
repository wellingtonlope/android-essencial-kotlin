package br.com.livroandroid.carros.domain

import android.content.Context
import android.util.Log
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.getText
import br.com.livroandroid.carros.extensions.getXml
import org.json.JSONArray

object CarroService {
    private val TAG = "livro"

    // Busca os carros por tipo (clássicos, esportivos ou luxo)
    fun getCarros(context: Context?, tipo: TipoCarro): List<Carro> {
        // Este é o arquiovo que temos que ler
        val raw = getArquivoRaw(tipo)
        // Abre o arquivo para leitura
        val resource = context?.resources
        val inputStream = resource?.openRawResource(raw)
        inputStream?.bufferedReader().use {
            // Lẽ o XML e cria a lista de carros
            val json = it?.readText()
            // Conveter o JSON para List<Carro>
            return fromJson(json)
        }
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
