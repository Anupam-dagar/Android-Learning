package demo

fun main(args: Array<String>){
    // Ranges
    val oneTo10 = 1..10
    val alpha = "A".."Z"
    println("R in Alpha : ${"R" in alpha}")

    val tenTo1 = 10.downTo(1)
    val twoTo20 = 2.rangeTo(20)
    val rng3 = oneTo10.step(3)
    for(x in rng3) println("rng3 : $x")

    for(x in tenTo1.reversed()) println("Reverse : $x")
}