import com.sun.org.omg.CORBA.ParameterDescription
import java.lang.Compiler.command
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.system.exitProcess



fun main(Args: Array<String>){
    Game.play()
}



object Game{

  private  val player = Player("Alex")
  private  var currentRoom: Room =  TownSquare()

  private var worldMap = listOf(
          listOf(currentRoom, Room("Tavern"), Room("Back Room")),
          listOf(Room("Long Corridor"), Room("Generic Room")))
    private fun move(directiontionInput: String) =
            try {
                val direction = Direction.valueOf(directiontionInput)
                val newPosition = direction.updateCoordinate(player.currentPosition)
                if(!newPosition.isInBounds){
                    throw IllegalStateException("$direction is out of bounds")
                }

                val newRoom = worldMap[newPosition.y][newPosition.x]
                player.currentPosition = newPosition
                currentRoom = newRoom
                "Ok, you move $direction to the ${newRoom.name}. \n${newRoom.load()}"

            }catch(e: Exception){
                "Invalid direction: $directiontionInput."
            }
    private fun quit(){
         println("Quit Game")

     }
    private fun fight() = currentRoom.monster?.let {
        while(player.healthpoint > 0 && it.healthpoint > 0){
            slay(it)
            Thread.sleep(1000)
        }

        "Combat complete."
    }?: "There's nothing to fight."

    private fun slay(monster: Monster) {
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if(player.healthpoint < 0){
            println(">>>> You have been defeated! Thanks for playing. <<<<")
            exitProcess(0)
        }

        if(monster.healthpoint <=0){
            println(">>>> ${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }


    private fun ring(){
        println(TownSquare().ringBell())
    }


    init {
        println("Welcome, adventures")
        player.castFireball()


    }

    fun  play() {
        while (true){
            println(currentRoom.description())
            println(currentRoom.load())

            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
            if(readLine() == "quit"){
                break
            }
        }
    }

  private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()})" +
                "Blessed: ${if(player.isBlessed) "Yes" else "No"})")
        println("${player.name} ${player.fomathealthStatus()}")
    }


    private  class  GameInput(arg: String?){
        private  val  input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { " " })

        fun  processCommand() = when(command.toLowerCase()){
            "move" -> move(argument)
            "quit" -> quit()
            "ring" -> ring()
            "fight" -> fight()
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"

    }


}




