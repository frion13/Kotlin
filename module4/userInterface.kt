package module4

import java.util.Scanner

fun main(){
    val fleet: MutableList<Aircraft> = mutableListOf(
        PassengerAircraft(320, 6100, 24000.0, 180),
        PassengerAircraft(737, 5500, 20000.0, 150),
        CargoAircraft(745, 9000, 50000.0, 45000)
    )

    val scanner = Scanner(System.`in`)

    while (true){
        println("""
            **Меню авиакомпании**
            1. Все самолеты
            2. Пассажирские самолеты
            3. Грузовые самолеты
            0. Выход
        """.trimIndent())
        print("Введите номер команды: ")

        when(scanner.nextLine()){
            "1" -> fleet.forEach({it.printInfo()})
            "2" -> fleet.filterIsInstance<Passanger>().forEach({it as Aircraft; it.printInfo()})
            "3" -> fleet.filterIsInstance<Cargo>().forEach({it as Aircraft; it.printInfo()})
            "0" -> break
        }
    }
}