package demo

fun main(args: Array<String>) {
    // Strings
    val myName = "John"
    val longStr = """This is a
        multilinestring
    """
    var fName: String = "Doug"
    var lName: String = "Smith"

    fName = "Sally"
    var fullName = fName + " " + lName
    println("Name : $fullName")
    println("1 + 2 = ${1 + 2}")
    println("String Length : ${longStr.length}")

    var str1 = "A random string"
    var str2 = "a random string"

    println("Strings Equal : ${str1.equals(str2)}")
    println("Compare A to B: ${"A".compareTo(("B"))}")

    println("2nd Index : ${str1[2]}")
    println("Index 2-7 : ${str1.subSequence(2,8)}")
    println("Contains random : ${str1.contains("random")}")
}