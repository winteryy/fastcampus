package ch2

fun main(){

    val test = Test()
    Test().hello()
    test.hi()

}

fun Test.hi() = println("하이")


class Test(){

    fun hello() = println("안녕")
    fun bye() = println("잘가")
}