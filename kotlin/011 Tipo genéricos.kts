fun <T> toList(vararg args: T): List<T> {
    val list = ArrayList<T>()
    for (s in args)
        list.add(s)
    return list
}

val strings = toList<String>("Ricardo", "Rodrigues", "Lecheta")
println(strings)
val ints = toList<Int>(1, 2, 3, 4, 5)
println(ints)
