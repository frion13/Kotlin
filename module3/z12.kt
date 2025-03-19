package module3

/*
Задание №12
Високосный год
Напишите функцию, которая получает в параметры какой-либо год, проверяет високосный ли это год и возвращает результат проверки.
*/

fun main() {
    val year = 2024
    println("$year високосный? ${isLeapYear(year)}")
}

fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}
