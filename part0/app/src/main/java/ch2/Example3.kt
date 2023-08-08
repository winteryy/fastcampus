package ch2

fun main() {

    // let, run, apply, also, with

    // 1. let
    val a = 3
    a.let {}
    var user: User? = User("채상아", 10, true)
    val age = user?.let{
        it.age
    }
    // 수신객체.let{ it(명시적으로 나타내도 됨) ->
    //
    // 마지막 줄 // return 값
    // }

//    val age = user.let{ user ->
//        user.age
//    }
    println(age)

    // 2. run
    // 수신객체.run { this ->
    //
    // 마지막 줄 // return 값
    // }
    val kid = User("아이", 4, false)
    val kidAge = kid.run{
        age
    }
    println(kidAge)

    // 3. apply
    // 수신객체.apply { this ->
    //
    // asdadsd
    // }
    // return 값이 수신객체
    val kidName = kid.apply{
        name
    }
    println(kidName)

    val female = User("슬기", 20, true, true)
    val femaleValue = female.apply {
        hasGlasses = false
    }
    println(femaleValue.hasGlasses)

    // 4. also
    // 수신객체.also { it ->  //local variable 선언 가능
    //
    // } // return 수신객체(자기자신)

    val male = User("민수", 17, false, true)
    val maleValue = male.also {
        it.name
        it.hasGlasses = false
        println(it.name)
    }
    println(maleValue)

    // 5. with
    // with(수신객체) {
    //
    // 마지막 줄 // return 값
    // }

    val result = with(male){
        hasGlasses = false
        true
    }

}

class User(
    val name: String,
    val age: Int,
    val gender: Boolean, //true:female, false:male
    var hasGlasses: Boolean = true,
)