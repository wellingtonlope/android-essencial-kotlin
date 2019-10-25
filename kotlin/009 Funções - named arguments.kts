teste("Ricardo", "Lecheta", "Novatec")
teste("Ricardo")
teste("Ricardo", editoria = "Novatec")

fun teste(nome: String?, sobrenome: String? = null, editoria: String? = null) {
    println("Nome: $nome, Sobrenome: $sobrenome, Editora: $editoria")
}
