package threading


fun main(args: Array<String>) {
    val range = 1..100

    val evenThread = Thread {
        for (number in range)
            if (number % 2 == 0) println("Even number is: $number")
    }

    val oddRunnable = Runnable {
        for (number in range)
            if (number % 2 == 1) println("Odd number is: $number")
    }

    val oddThread = Thread(oddRunnable)

    evenThread.start()
    oddThread.start()

    evenThread.join()
    oddThread.join()
}


