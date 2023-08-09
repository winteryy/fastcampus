package ch2

fun main(){
    val person = Person("수지", 24)
    val dog = Dog("해피", 2)

    println(person.toString())
    println(dog.toString())
    println(dog.copy(age=3).toString())

    val cat: Cat = BlueCat()
    val result = when(cat){
        is BlueCat -> "blue"
        is RedCat -> "red"
        is GreenCat -> "green"
    }

    println(result)

}

class Person(
    val name: String,
    val age: Int,
)

data class Dog(
    val name: String,
    val age: Int,
) {
//    override fun toString(): String {
//        return "직접 구현 $name"
//    }
}

//data class Corgi(
//    val pretty: Boolean = true
//): Dog()

sealed class Cat
class BlueCat: Cat()
class RedCat: Cat()
class GreenCat: Cat()