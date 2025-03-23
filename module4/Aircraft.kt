package module4

/*
Задание 17: Авиакомпания 1
Поможем авиакомпании в создании программы для учета авиапарка.

Создайте класс Aircraft и реализуйте в нем свойства,
содержащие следующее: номер воздушного судна, максимальная дальность полета, вместимость бака,
расход топлива на 100 км. Установите значения по своему усмотрению.

Переопределите геттер свойства содержащего расход топлива, чтобы он самостоятельно
рассчитывался из свойств дальности полета и вместимости бака.



 class Aircraft(aircraftNum: Int, maxFlightRange: Int, capacity: Int) {
    val aircraftNum: Int = 1210
    val maxFlightRange: Int = 1000
    val capacity: Double = 100.0
    val fuel: Double
        get() = capacity / maxFlightRange * 100
}

 */



abstract class Aircraft(
    protected val aircraftNum: Int,
    protected val maxFlightRange: Int,
    protected val capacity: Double){
    protected val fuel: Double
        get() = capacity/maxFlightRange * 100



    open fun printInfo(){
        println("""Самолет №$aircraftNum
            Макс дальность полета: $maxFlightRange км"
            Вместимость бака: $capacity л
            Расход топлива: $fuel л/100 км
        """.trimMargin())

    }
}








