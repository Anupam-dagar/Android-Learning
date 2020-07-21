package demo

fun main(args: Array<String>){
    var nullVal: String? = null

    fun returnNull(): String?{
        return null
    }

    var nullVal2 = returnNull()
    if(nullVal2 != null){
        println("nullVal2.length")
    }
    var nullVal3 = nullVal2!!.length
    var nullVal4: String = returnNull() ?: "No Name"
}