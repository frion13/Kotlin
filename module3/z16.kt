package module3

import kotlin.math.sqrt

/*
Задание №16
Корень числа
Напишите функцию для переменных типа Double, возвращающую корень числа

*/

fun main() {
    val num = 25.0;
    println("Корень $num равен ${num.squareRoot()}")
}

fun Double.squareRoot(): Double = sqrt(this)




