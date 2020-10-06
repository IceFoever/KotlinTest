
import java.io.File

class Player(_name:String,
                      override var healthpoint: Int = 100,
                      val isBlessed: Boolean,
                      private val isImmortal: Boolean) : Fightable{




    var name = _name
        get() = "${field.capitalize()} of $hometown"
       private set(value) {
            field = value.trim()
       }
    val hometown by lazy { selectHometown()}
    private fun selectHometown() = File("data/towns.txt")
            .readText()
            .split("\n")
            .random()
    var currentPosition = Coordinate(0,0)

    init {
        require(healthpoint > 0, { "healthPoint must be greater than zero"})
        require(name.isNotBlank(),{"Player must have a name"})
    }


    constructor(name: String): this(name,
    isBlessed = true,
    isImmortal = false){
        if (name.toLowerCase() == "kar") healthpoint = 40
    }



    fun fomathealthStatus() = when (healthpoint) {
            100 -> "is in excellent life"
            in 90..99 -> "has a few scratches"
            in 75..89 -> if (isBlessed){
                "has some minor wounds but is healing quite quickly"
            } else {
                "has some minor wounds"
            }
            in 15..50 -> "looks pretty hurt"
            else -> "is in awful condition "
        }


    fun auraColor(): String {
        val auraVisible = isBlessed && healthpoint > 50 || isImmortal
        val auraColor = if (auraVisible) "Green" else "NONE"
        return auraColor
    }

    fun  castFireball(numFireballs: Int = 2 )=
            println("A glass og Fireball springs into existence." +
                    "(x$numFireballs)")

    override val diceCount = 3

    override val diceSides = 6


    override fun attack(attackpoint: Fightable): Int {
          val damageDealt = if(isBlessed){
              damageRoll * 2
          }else{
              damageRoll
          }
        attackpoint.healthpoint -= damageRoll
        return damageDealt
    }


}





