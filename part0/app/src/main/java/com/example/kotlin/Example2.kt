package com.example.kotlin

fun main(){

    val result = test(1, c=5)
    test2(id="상아님", name="채상아", nickname="상아")
    println(result)
    println(times(1,3))
}

// 2. 함수
fun test(a: Int, b: Int=3, c: Int=4): Int{
    println(a+b)
    return a+b
}

fun test2(name: String, nickname: String, id: String) = println(name + nickname + id)

fun times(a: Int, b: Int) = a*b