fun toList(vararg args: String): List<String> {
    val list = ArrayList<String>()
    for (s in args) {
        list.add(s)
    }
    return list
}

var list = toList("Ricardo", "Rodrigues", "Lecheta")
println(list)
