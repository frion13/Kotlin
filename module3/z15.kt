package module3

/*
Задание №15
Лямбда
Создайте переменную, которая содержит лямбда выражение для вывода в консоль данных
массива типа String.

*/

fun main() {
    val printArrayString: (Array<String>) -> Unit = { it.forEach(::println) }

    printArrayString(arrayOf("Hello", "world"))
}





