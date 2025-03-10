package module2

/*
Задание №8
Проценты по вкладу
Напишите программу для расчета дохода по вкладу.
Пользователь вводит сумму вклада, длительность вклада (количество месяцев) и ежемесячный процент по вкладу.
Программа выводит в консоль информацию по каждому месяцу отдельно. Информация отображает:
 на сколько будет увеличиваться вклад в этом месяце и итоговую сумму вклада в данном месяце.


*/


fun main() {
    print("Введите сумму вклада: ")
    val deposit = readLine()?.toDoubleOrNull() ?: return

    print("Введите длительность вклада (в месяцах): ")
    val months = readLine()?.toIntOrNull() ?: return

    print("Введите ежемесячный процент (%): ")
    val monthlyRate = readLine()?.toDoubleOrNull() ?: return

    var totalAmount = deposit

    println("\nДетализация начисления процентов:")
    println("Месяц | Начисленные проценты | Итоговая сумма")

    for (month in 1..months) {
        val interest = totalAmount * (monthlyRate / 100)
        totalAmount += interest
        println("$month     | ${"%.2f".format(interest)} руб. | ${"%.2f".format(totalAmount)} руб.")
    }

    println("\nОбщая сумма вклада после $months месяцев: ${"%.2f".format(totalAmount)} руб.")

}
