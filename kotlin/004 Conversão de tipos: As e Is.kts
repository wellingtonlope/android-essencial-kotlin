val s: Any = "Ricardo"
println(s as String)
println(s as? Int)
if(s is String){
    println("$s é uma String") // smart cast
}
