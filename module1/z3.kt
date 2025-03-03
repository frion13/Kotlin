package module1/* Задание 3
Преобразуйте var five: Char = '5' в
String
Byte
Short
Int
Double
FLoat
Long
*/

fun main(args: Array<String>) {
    val five: Char = '5'
    println(five.toString() is String)
    println(five.code.toByte() is Byte)
    println(five.code.toShort() is Short)
    println(five.code is Int)
    println(five.code.toDouble() is Double)
    println(five.code.toFloat() is Float)
    println(five.code.toLong() is Long)



}
