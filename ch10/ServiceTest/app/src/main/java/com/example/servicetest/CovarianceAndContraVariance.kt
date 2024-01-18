package com.example.servicetest

import androidx.constraintlayout.widget.ConstraintSet.Transform

open class Person(val name: String, val age: Int)
class Student(name: String, age: Int) : Person(name, age)
class Teacher(name: String, age: Int) : Person(name, age)

// 使用out声明，声明的是SimpleData使用T的方式
// 禁止in T使得开放类型上界后，T也不会误赋值兄弟类型
// 从而导致类型转换异常
class SimpleData<out T>(val data: T?) {
//    private var data: T? = null
//
//    fun set(t: T?) {
//        data = t
//    }

    fun get(): T? {
        return data
    }
}

fun handleSimpleData(data: SimpleData<Person>) {
    val personData = data.get()
    if (personData != null) {
        println("person name:${personData.name}, age:${personData.age}")
    }
}

fun covariance() {
    val student = Student("Tom", 19)
    val data = SimpleData<Student>(student)

    // 赋值检查被挪到构造函数，
    // 此时的类型检查可以检查出兄弟类型问题
    // 取消注释可以看到编译错误
    // val teacher = Teacher("Frank", 26)
    // val data = SimpleData<Student>(teacher)

    // Student是Person的子类，但SimpleData<Student>不是SimpleData<Person>的子类
    // 也就是说SimpleData<T>不是协变的
    // 因为协变会导致上界提升，当T可写时，会造成类型转换问题
    // 也就是handleSimpleData中Teacher类被塞入SimpleData<Student>，又被当作Student返回。
    handleSimpleData(data = data)
    val studentData = data.get()
    println("student name:${studentData?.name}, age:${studentData?.age}")
}

interface Transformer<in T> {
    fun transform(t: T): String
}

fun handleTransformer(trans: Transformer<Student>) {
    val student = Student("Allen", 19)
    val result = trans.transform(student)
    println("student transform result: $result")
}

fun contravariance() {
    val trans = object : Transformer<Person> {
        override fun transform(t: Person): String {
            return "${t.name} ${t.age}"
        }
    }
    handleTransformer(trans)
}

fun main() {
    covariance()
    contravariance()
}