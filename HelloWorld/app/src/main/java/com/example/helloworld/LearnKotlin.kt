package com.example.helloworld

import java.util.Locale
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

    lambda()
}

fun lambda() {
    // collections
    val list = ArrayList<String>()
    list.add("Apple")
    list.add("Banana")
    list.add("Orange")
    list.add("Pear")
    list.add("Grape")

    val list2 = listOf("Apple", "Banana", "Orange", "Pear", "Grape")

    for (fruit in list) {
        println(fruit)
    }

    val map = mapOf("apple" to 1, "banana" to 2, "orange" to 3, "pear" to 4, "grape" to 5)

    for ((fruit, number) in map) {
        println("fruit is $fruit, number is $number")
    }

    var maxLengthFruit = ""
    for (fruit in list2) {
        if (fruit.length > maxLengthFruit.length) {
            maxLengthFruit = fruit
        }
    }
    println("max length fruit is $maxLengthFruit")

    val maxLengthFruit2 = list.maxBy { it.length }
    println("max length fruit is $maxLengthFruit2")

    val lambda = { fruit: String -> fruit.length }
    val maxLengthFruit3 = list.maxBy(lambda)
    val maxLengthFruit4 = list.maxBy({ fruit: String -> fruit.length })
    val maxLengthFruit5 = list.maxBy() { fruit: String -> fruit.length }
    val maxLengthFruit6 = list.maxBy { fruit: String -> fruit.length }
    val maxLengthFruit7 = list.maxBy { fruit -> fruit.length }

    val newList = list.map { it.uppercase() }
    for (fruit in newList) {
        println(fruit)
    }

    val newList2 = list.filter { it.length <= 5 }
                       .map { it.uppercase() }
    for (fruit in newList) {
        println(fruit)
    }

    val anyResult = list.any { it.length <= 5 }
    val allResult = list.all { it.length <= 5 }
    println("anyResult is $anyResult, allResult is $allResult")

    val thread = Thread(object : Runnable {
        override fun run() {
            println("Thread is running")
        }
    }).start()

    Thread {
        println("Another thread is running")
    }.start()

    printParams(123)

    // pass parameters by key-value
    printParams(str = "world", num = 123)
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

fun doStudyUnsafe(study: Study?) {
    study?.readBooks()
    study?.doHomework()
}

fun getTextLength(text: String?) = text?.length ?: 0;

fun nullPointerException() {
    doStudyUnsafe(null)

    if (content != null) {
        printUpperCase()
    }
}

var content: String? = "Hello"

fun printUpperCase() {
    val upperCase = content!!.uppercase()
    println(upperCase)
}

// parameter default value
fun printParams(num: Int, str: String = "hello") {
    println("num is $num, str is $str")
}


