val String.numVowels
    get() = count{"aeiouyb".contains(it)}

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun <T> T.easypoint(): T{
     println(this)
     return this
}

fun main(){

    "Madrigal has left the building".easypoint().addEnthusiasm(3).easypoint()
    42.easypoint()
    "How many vowels?".numVowels.easypoint()

}

