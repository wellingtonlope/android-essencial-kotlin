fun String.ascii(): String {
    var s = ""
    for (c in this) {
        s += c.toInt()
    }
    return s
}

val nome = "Ricardo"
val ascii = nome.ascii()
println(ascii)

fun List<Int>.filtrar(filtro: (Int) -> (Boolean)): List<Int> {
    val newList = arrayListOf<Int>()
    for (item in this) {
        if (filtro(item)) {
            newList.add(item)
        }
    }
    return newList
}

val ints = listOf(1,2,3,4,5)
println(ints)
// Apenas números pares
var list = ints.filtrar { it % 2 == 0 }
println(list)
// Apenas números maiores que 3
list = ints.filtrar { it > 3 }
println(list)
// Apenas números ímpares
list = ints.filtrar { it % 2 == 1 }
println(list)
