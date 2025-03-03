package module1/*
Задание №5
Алфавит
Создайте переменную, содержащую последовательность английского
алфавита наоборот – начиная с последней буквы алфавита и заканчивая первой буквой.
*/

fun main(args: Array<String>) {
 val alphabet = ('Z' downTo 'A').joinToString(" ")
    println(alphabet)
}
