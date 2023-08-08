package com.example.kotlin

fun main(){
    max(10, 3)
    isHoliday("일")
}

fun max(a: Int, b: Int) {

//    if(a>b){
//        println(a)
//    } else{
//        println(b)
//    }

    val result = if(a>b){
        a
    } else{
        b
    }

    val result2= if(a>b) a else b
    println(result)
}

//input : 월 화 수 목 금 토 일
fun isHoliday(dayOfWeek: String){

    val result = when(dayOfWeek){
        "토",
        "일" -> if(dayOfWeek=="토") "좋아" else "약간 좋아"
        in listOf("월", "화", "수") -> "진짜 싫어"
        else -> "안 좋아"

    }

    println(result)

}