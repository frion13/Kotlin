package module2

/*
Задание №9
Массив Стран
Создайте многомерный массив, где в первом массиве хранится названия каких-либо стран,
в следующем столицы данных стран и в последнем массиве используемая денежная единица.
Сохраните информацию минимум по 3 странам и выведите в консоль в виде:
Страна Столица Валюта

*/


fun main() {
    val countries = arrayOf(
        arrayOf(
            "Россия", "Франция", "Япония"
        ),
        arrayOf(
            "Москва", "Париж", "Токио"
        ),
        arrayOf(
            "Рубль", "Евро", "Иена"
        )
    )
    println("Страна | Столица | Валюта")
    println("___________________________")

    for (country in countries[0].indices){
        println("${countries[0][country]} | ${countries[1][country]} | ${countries[2][country]}")
    }



}
