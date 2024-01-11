package com.example.broadcastbestpractice

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

inline fun num1AndNum2Inline(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

fun normalFun() {
    println("normal function")
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1, num2, ::plus)
    val result2 = num1AndNum2(num1, num2, ::minus)
    println("result1 is $result1")
    println("result2 is $result2")
}

fun lambda() {
    println("lambda")
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1, num2) { n1, n2 -> n1 + n2 }
    val result2 = num1AndNum2(num1, num2) { n1, n2 -> n1 - n2 }
    println("result1 is $result1")
    println("result2 is $result2")
}

// java.lang.StringBuilder.() -> Unit
// 表示() -> Unit这个函数类型定义在java.lang.StringBuilder中
// 使得该函数类型对应的block执行时自动拥有java.lang.StringBuilder的上下文（this为java.lang.StringBuilder示例）
// 这里就是调用build的对象
fun StringBuilder.build(block: java.lang.StringBuilder.() -> Unit): java.lang.StringBuilder {
    block()
    return this
}

fun funContext() {
    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
    val result = StringBuilder().build {
        append("Start eating fruits.\n")
        for (fruit in list) {
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result.toString())
}

fun inline() {
    println("inline")
    val num1 = 100
    val num2 = 80
    val result = num1AndNum2Inline(num1, num2) {
        a, b -> a + b
    }
    println("result is $result")
}

fun printString(str: String, block: (String) -> Unit) {
    println("printString begin")
    block(str)
    println("printString end")
}

inline fun printStringInline(str: String, block: (String) -> Unit) {
    println("printStringInline begin")
    block(str)
    println("printStringInline end")
}

// 测试内联函数与非内联函数的区别之一：return语句
fun funReturn() {
    println("funReturn start")
    val str = ""
    printString(str) { s ->
        println("lambda start")
        // 普通lambda中不能直接使用return关键字
        // 结果：只有lambda end不会被打印，其余都会被打印
        if (s.isEmpty()) return@printString
        println(s)
        println("lambda end")
    }
    printStringInline(str) { s ->
        println("lambda start")
        // 内联lambda中可以直接使用return关键字
        // 结果：因为高阶函数printStringInline和lambda都是内联的
        // 因此所有end都不会被打印
        // 可以看反汇编之后的java code，就是原地展开了
        if (s.isEmpty()) return
        println(s)
        println("lambda end")
    }
    println("funReturn end")
}

inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
        // 无法直接使用
        // return
        // 可以使用
        // return@Runnable
    }
    runnable.run()
}

fun crossinline() {
    // 这其实是一个预期管理问题，调用者看了一眼你的函数声明
    // 发现是inline的，自然对于return的预期就是全局返回。
    // 而如果内部有匿名类，就会打破这个预期，造成语义的二义性
    // 于是报错
    runRunnable {
        println("runRunnable")
        // 因为crossinline，入参lambda无法再使用return
        // return
        return@runRunnable
    }
}

fun main() {
    normalFun()
    lambda()
    funContext()
    inline()
    funReturn()
    crossinline()
}