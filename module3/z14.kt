package module3

/*
Задание №14
Инфо сотрудника
Напишите функцию, которая выводит в консоль информацию сотрудника
(ФИО, Возраст, Должность и Семейное положение).
С помощью перезагрузки функции, сделайте варианты, когда известны только:
ФИО и Должность
ФИО, Возраст и Должность
ФИО, Семейное положение и Должность
ФИО, Возраст, Семейное положение и Должность

*/

fun main() {
    employeeInfo("Иванов Иван", 20, "курьер", "не женат")
    employeeInfo("Иванов Иван", "курьер")
    employeeInfo("Иванов Иван", 20, "курьер")
    employeeInfo("Иванов Иван", "курьер", "не женат")
}

fun employeeInfo(employee: String, age: Int, position: String, status: String) {
    println("ФИО: $employee, возраст: $age, должность: $position, семейное положение: $status")
}

fun employeeInfo(employee: String, position: String) {
    println("ФИО: $employee, должность: $position")
}

fun employeeInfo(employee: String, age: Int, position: String) {
    println("ФИО: $employee, возраст: $age, должность: $position")
}

fun employeeInfo(employee: String, position: String, status: String) {
    println("ФИО: $employee должность: $position, семейное положение: $status")
}


