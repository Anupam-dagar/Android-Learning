package demo

import java.util.Random

fun main(args: Array<String>) {
    for(x in 1..10) {
        println("Loop : $x")
    }

    val rand = Random()
    val MagicNum = rand.nextInt(50) + 1

    var guess = 0

    while(MagicNum != guess){
        guess += 1
    }

    println("Magic Number was $guess")

    for(x in 1..20){
        if(x % 2 == 0){
            continue
        }

        println("Odd : $x")

        if(x == 15) break
    }

    var arr3: Array<Int> = arrayOf(3,6,9)

    for(i in arr3.indices){
        println("Multiple of 3: ${arr3[i]}")
    }

    for((index, value) in arr3.withIndex()){
        println("Index : $index Value : $value")
    }
}