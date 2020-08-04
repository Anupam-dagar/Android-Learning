package demo

fun main(args: Array<String>) {
    // variable declarations
    val name = "Anupam"
    var myAge = 42
    var bigInt: Int = Int.MAX_VALUE
    var smallInt: Int = Int.MIN_VALUE

    println("Biggest Int : " + bigInt)
    println("Smallest Int: $smallInt")

    // boolean
    if (true is Boolean) {
        println("true is boolean")
    }

    var letterGrade: Char = 'A'
    println("A is a Char : ${letterGrade is Char}")
}