package module4

data class Car(
    val brand: String,
    val color: String,
    val number: String
)

fun main(){
    val car = Car("Toyota", "Red", "A123BB")
    println(car)
}