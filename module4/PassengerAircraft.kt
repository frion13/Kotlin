package module4

class PassengerAircraft(
    model: Int,
    maxFlightRange: Int,
    capacity: Double,
    override val passengerCapacity: Int
) : Aircraft(model, maxFlightRange, capacity), Passanger{

    override fun printInfo() {
        super.printInfo()
        println("Пассажирских мест: $passengerCapacity")
    }
}