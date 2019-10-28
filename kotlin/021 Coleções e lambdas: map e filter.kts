val nomes = listOf("Ricardo", "Lecheta")
// Lista para letra maiusculas
val upperCaseList = nomes.map { it.toUpperCase() }
println(upperCaseList)
// Filtrar apenas os pares
val ints = listOf(1,2,3,4,5)
val pares = ints.filter { it % 2 == 0 }
println(pares)
