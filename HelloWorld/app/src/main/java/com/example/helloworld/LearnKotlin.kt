package com.example.helloworld

import kotlin.math.max

fun main() {
    println("Hello Kotlin!")

    // variable and function
    val a = 10
    var b: Int = 10
    b *= 10
    println("a = $a, b = $b")

    val larger = largerNumber(a, b)
    println("larger number is $larger")

    checkNumber(a)
    val c = 10L
    checkNumber(c)

    // range and for-in
    for (i in 0..10) {
        println(i)
    }

    val range = 0 until 10

    for (i in range step 2) {
        println(i)
    }

    val p = Person("Jack", 19)
    p.eat()

    val student = Student("a123", 5, "Jack", 19)

    val student2 = Student("Jack", 19)
    doStudy(student2)

    // data class
    val cellphone1 = Cellphone("apple", 1299.99)
    val cellphone2 = Cellphone("apple", 1299.99)
    println(cellphone1)
    println("cellphone1 equals cellphone2 " + (cellphone1 == cellphone2))

    Singleton.singletonTest()
}

fun doStudy(study: Study) {
    study.readBooks()
    study.doHomework()
}

fun largerNumber(num1: Int, num2: Int) = max(num1, num2)

fun largerNumberIfEdition(num1: Int, num2: Int): Int {
    val value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}

fun largerNumber3(num1: Int, num2: Int) = if (num1 > num2) {
    num1
} else {
    num2
}

fun getScore(name: String) = if (name == "Tom") {
    86
} else if (name == "Jim") {
    77
} else if (name == "Jack") {
    95
} else if (name == "Lily") {
    100
} else {
    0
}

fun getScoreWhenEdition(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}

fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

