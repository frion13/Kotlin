package module1/* Задание №4
Приветствие пользователя
Напишите программу, запрашивающую имя пользователя и
выводящую приветствие пользователя в консоль, после ввода имени.
*/

fun main(args: Array<String>) {
    println("Введите имя ")
    var userName: String? = readLine()
    println("Привет, $userName")
}
