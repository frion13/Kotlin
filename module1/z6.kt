package module1/*
Задание 6: Сравнение
Напишите программу, которая запрашивает у пользователя два числа в переменные “a” и “b”.
Проверьте больше ли "a" чем "b" и получите результат в виде переменой типа boolean.

*/

fun main(args: Array<String>) {
    println("Введите число а")
    val a: Int = readLine()?.toInt() ?: 0
    println("Введите число b")
    val b: Int = readLine()?.toInt() ?: 0

    val isGreater: Boolean = a > b
    println(isGreater)
}
