package com.example.kotlin

fun main(){
    var name: String = "상아"
//    name = null
    var number: Int = 10
//    number = 10

    var nickname: String? = null
    var secondNumber: Int? = null

//    val result = if(nickname == null) {
//        "값이 없음"
//    }else{
//        nickname
//    }

    val size = nickname?.length

    val result = nickname?: "값이 없음"




    println(result)
}