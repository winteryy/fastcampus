package com.example.kotlin

fun main(){
//    println(check("안녕"))
//    println(check(3))
//    println(check(true))

    println(smartcast("안녕"))
    println(smartcast(123))
    println(smartcast(true))

}

fun check(a: Any): String{
    return when (a) {
        is String -> {
            "문자열"
        }

        is Int -> {
            "정수"
        }

        else -> {
            "모름"
        }
    }
}

fun cast(a: Any){
//    val result = a as? String
    val result = (a as? String) ?: "실패"
    println(result)
}

fun smartcast(a: Any): Int{
    return if(a is String){
        a.length
    }else if(a is Int){
        a.dec()
    }else{
        -1
    }
}