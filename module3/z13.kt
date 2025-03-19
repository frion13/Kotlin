package module3

/*
Задание №13
Количество сотрудников
Напишите функцию, принимающую на вход имена сотрудников и возвращающую их количество.
Используйте для этого vararg, из предыдущей темы.

*/

fun main() {
    val count: Int = countEmployee("Федор", "Александр", "Георгий", "Максим")
    println(count)
}

fun countEmployee(vararg employees: String): Int {
    var count = 0
    for (employee in employees) {
        count += 1
    }
    return count
}


