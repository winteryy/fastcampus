package ch2

fun main() {

    val a = fun(){ println("Hello") }
    val b: (Int) -> Int = {
        it * 10
    }
    val c = { i: Int, j: Int -> i * j}
    val d: (Int, String, Boolean) -> String = { _, b, _ -> b}

    println(b(10))
    println(c(5,4))

    hello(10, b)

}

fun hello(a: Int, b: (Int) -> Int){
    println(a)
    println(b(20))
}