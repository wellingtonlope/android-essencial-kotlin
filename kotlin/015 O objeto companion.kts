data class Carro(val nome: String)

class CarroService {
    companion object {
        fun getCarros(): List<Carro> {
            val carros = mutableListOf<Carro>()
            for (i in 1..3) {
                val c = Carro("Carro $i")
                carros.add(c)
            }
            return carros
        }
    }
}


val carros = CarroService.getCarros()
for (c in carros) {
    println(c)
}
