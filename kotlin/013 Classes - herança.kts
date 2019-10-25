open class Automovel(nome: String, ano: Int) {
    val nome: String
    val ano: Int
    init {
        this.nome = nome
        this.ano = ano
    }

    open fun acelerar(velocidade: Int){
        print("Acelerando este automóvel $velocidade")
    }

    override fun toString(): String {
        return "Automóvel $nome, ano $ano"
    }
}

class Carro(nome: String, ano: Int): Automovel(nome, ano) {
    override fun acelerar(velocidade: Int) {
        print("Acelrando este carro $velocidade")
    }
}

val c1 = Carro("Fusca", 1950)
println(c1)
println("Carro ${c1.nome}, ano: ${c1.nome}")
c1.acelerar(100)
