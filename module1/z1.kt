package module1

//Задание 1
fun main(args: Array<String>) {
    println("hello Kotlin")

    // 1 Создайте переменную со значением null типа Any
    var any: Any? = null
    println(any)


    //2 Переопределите значение ранее созданной переменной на любую цифру

    any = 10
    println(any)


    //3 Создайте переменную, не указывая ее тип и присвойте ей значение из ранее созданной переменной
    //деленную на 2,5

    val anyVar = any / 2.5
    println(anyVar)

    //4 Напишите в комментарии какого типа последняя переменная
    //double
    println(anyVar::class)
}






