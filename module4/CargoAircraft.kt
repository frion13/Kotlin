package module4

class CargoAircraft(
    model: Int,
    maxFlightRange: Int,
    capacity: Double,
    override val loadCapacity: Int
) : Aircraft(model, maxFlightRange, capacity), Cargo{

    override fun printInfo() {
        super.printInfo()
        println("Грузоподъемность: $loadCapacity")
    }
}