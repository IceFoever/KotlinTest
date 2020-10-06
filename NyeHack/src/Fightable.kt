import kotlin.random.Random

interface  Fightable{
    var healthpoint: Int
    val diceCount: Int
    val diceSides: Int
    val damageRoll: Int
        get() = (0 until diceCount).map {
            Random.nextInt(diceSides+1)
        }.sum()

    fun attack(attackpoint: Fightable): Int
}

abstract class Monster(val name: String,
                       val description: String,
                       override var healthpoint: Int ): Fightable{

    override fun attack(attackpoint: Fightable): Int {
        val  damageDealt = damageRoll
        attackpoint.healthpoint -= damageDealt
        return damageDealt
    }
}

class Goblin(name: String = "Goblin",
             description: String = "A nasty-looking goblin",
             healthpoint: Int = 30
             ): Monster(name, description, healthpoint) {
    override val diceCount = 2
    override val diceSides = 8
}