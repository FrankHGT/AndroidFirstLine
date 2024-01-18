package com.example.servicetest

import android.content.Context
import android.content.Intent

inline fun <reified T> getGenericType() = T::class.java

inline fun <reified T> startService(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startService(intent)
}

fun main() {
    val result1 = getGenericType<String>()
    val result2 = getGenericType<Int>()
    println("result1 is $result1")
    println("result2 is $result2")
}