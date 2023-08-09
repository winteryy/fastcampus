package ch2

import ch2.Book.Companion.NAME

fun main(){

    Counter
    println(Counter.count)
    Counter.countUp()
    Counter.countUp()
    println(Counter.count)

    Counter.hello()

//    Book.NAME
//    Book.create()

    NAME
}

object Counter: Hello() {
    init{
        println("카운터 초기화")
    }

    var count = 0
    fun countUp(){
        count++
    }
}

open class Hello(){

    fun hello() = println("Hello")

}

class Book(){

    companion object {
        const val NAME = "hello"
        fun create() = Book()
    }

}