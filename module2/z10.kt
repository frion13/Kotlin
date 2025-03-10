package module2

/*
Задание №10
Города
Напишите программу для хранения названия городов. Пользователь может сделать 4 действия:
Добавить название города в программу.
 Посмотреть в консоли список всех добавленных городов
Посмотреть список добавленных городов без повторений
Выход из программы
Для хранения используйте коллекцию типа List.

*/


fun main() {
    val cities = mutableListOf<String>();

    while (true) {
        println(
            """
            Выберите действие:
            1 - Добавить город
            2 - Посмотреть спимок городов
            3 - Посмотреть список городов без повторений
            4 - Выйти
        """.trimIndent()
        )
        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Введите название города: ")
                val city = readLine()?.trim()
                if (!city.isNullOrEmpty()){
                    cities.add(city)
                }
            }
            2 ->{
                if (cities.isNotEmpty()){
                    for (city in cities){
                        println(city)
                    }
                }else println("Список пуст")
            }
            3 ->{
                if (cities.isNotEmpty()){
                   val setCities = cities.toSet()
                    for (city in setCities){
                        println(city)
                    }
                }
            }
            4 ->{
                println("Выход из программы")
                break
            }
        }
    }

}
