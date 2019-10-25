fun filtrar(list: List<Int>, filtro: (Int) -> (Boolean)): List<Int> {
    val newList = arrayListOf<Int>()
    for (item in list) {
        if (filtro(item)) {
            newList.add(item)
        }
    }
    return newList
}

fun numerosPares(numero: Int) = numero % 2 == 0
fun numerosMaiorQue3(numero: Int) = numero > 3

val ints = listOf(1, 2, 3, 4, 5)
println(ints)
//var list = filtrar(ints, { numero: Int -> numerosPares(numero) })
//var list = filtrar(ints, { numerosPares(it) })
var list = filtrar(ints) { numerosPares(it) }
println(list)
list = filtrar(ints, ::numerosMaiorQue3)
println(list)
list = filtrar(ints) { it % 2 == 1 }
println(list)
