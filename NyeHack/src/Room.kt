open class Room(val name: String){
    protected open val dangerLevel =5
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\n" +
            "Danger level: $dangerLevel"+
            "Creature: ${monster?.description ?: "none."}"



   open fun load() = "Nothing much to see here..."
}

class TownSquare: Room("Town Square"){
    override val dangerLevel= super.dangerLevel-3
    private  var bellSound = "GWONG"

   final override fun load() = "The villagers rally and cheer as you enter\n" + "${ringBell()}"

    public fun ringBell() = "The bell tpwer announces your arrival. $bellSound"
}