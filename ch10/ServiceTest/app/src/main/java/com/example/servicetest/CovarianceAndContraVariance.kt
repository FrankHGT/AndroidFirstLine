package com.example.servicetest

open class Person(val name: String, val age: Int)
class Student(name: String, age: Int) : Person(name, age)
class Teacher(name: String, age: Int) : Person(name, age)

class SimpleData<T> {
    private var data: T? = null

    fun set(t: T?) {
        data = t
    }

    fun get(): T? {
        return data
    }
}

fun handleSimpleData(data: SimpleData<Person>) {
    val teacher = Teacher("Jack", 35)
    data.set(teacher)
}

fun main() {
    val student = Student("Tom", 19)
    val data = SimpleData<Student>()
    data.set(student)
    // Student是Person的子类，但SimpleData<Student>不是SimpleData<Person>的子类
    // 也就是说SimpleData<T>不是协变的
    // 因为协变会导致上界提升，当T可写时，会造成类型转换问题
    // 也就是handleSimpleData中Teacher类被塞入SimpleData<Student>，又被当作Student返回。
    handleSimpleData(data = data)
    val studentData = data.get()
}