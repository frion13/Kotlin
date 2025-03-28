package module4


fun main(){
    val fleet: MutableList<Aircraft> = mutableListOf()

    val  airbus = PassengerAircraft(
        model = 1212,
        maxFlightRange = 6100,
        capacity = 240000.0,
        passengerCapacity = 180
    )

    val boeing = PassengerAircraft(
        model = 1013,
        maxFlightRange = 8000,
        capacity = 260000.0,
        passengerCapacity = 230
    )

    val cargoBoeing = CargoAircraft(
        model = 7480,
        maxFlightRange = 8100,
        capacity = 190000.0,
        loadCapacity = 150000
    )

    fleet.add(airbus)
    fleet.add(boeing)
    fleet.add(cargoBoeing)

    for (plane in fleet){
        plane.printInfo()
    }
}