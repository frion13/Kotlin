package module4

 class Boeing747(
     aircraftNum: Int,
     maxFlightRange: Int,
     capacity: Double,
     override val passangerCapacity : Int) : Aircraft(aircraftNum, maxFlightRange, capacity), Passanger{

     override fun printInfo() {
         super.printInfo()
         println("Пассажиров: $passangerCapacity")
     }
}


fun main(){
    val aircraft = Boeing747(1101, 70, 70.0, 150)
    aircraft.printInfo()
}






