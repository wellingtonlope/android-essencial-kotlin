// cada lambda criada é feito um objeto e alocado na memoria
// inline fala ao compilador que o corpo dessa função deve ser copiada para dentro do código que está fazendo a chamada
inline fun List<Int>.filtrar(filtro: (Int) -> (Boolean)): List<Int> {
    val newList = arrayListOf<Int>()
    for (item in this) {
        if (filtro(item)) {
            newList.add(item)
        }
    }
    return newList
}
