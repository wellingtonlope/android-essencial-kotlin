var nome = "Ricardo"
imprimir(nome)
val soma = soma(2, 3)
imprimir("Soma: $soma")

//fun imprimir(s: String) = println(s)
fun imprimir(s: String): Unit { // o Unit pode ser omitido
    println(s)
}

//fun somar(a: Int, b: Int) = a + b
fun soma(a: Int, b: Int): Int {
    return a + b
}
