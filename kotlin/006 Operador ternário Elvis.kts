fun parOuImpar(a: Int): String {
    return if (a % 2 == 0) "par" else "ímpar"
}

println(parOuImpar(1))
println(parOuImpar(2))


fun enviarEmail(usuario: String, titulo: String? = null):String {
    val s = titulo?: "Bem vindo"
    return "$s $usuario"
}

println(enviarEmail("Ricardo"))
println(enviarEmail("Ricardo", "Welcome"))
