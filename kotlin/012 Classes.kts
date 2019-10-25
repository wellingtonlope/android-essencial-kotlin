class Carro(nome: String, ano: Int) {
    val nome: String
    val ano: Int

    init {
        this.nome = nome
        this.ano = ano
    }

    override fun toString(): String {
        return "Carro $nome, ano: $ano"
    }
}

val c1 = Carro("Fusca", 1950)
println(c1)
println("Carro ${c1.nome}, ano: ${c1.ano}")
