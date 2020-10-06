class LootBox<T>(vararg item: T){
    var open = false
    private var loot: Array<out T> = item

    operator fun get(index: Int): T? = loot[index].takeIf { open }

    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }

    fun <R> fetch(item: Int,lootModFuntion: (T) -> R): R? {
       return lootModFuntion(loot[item]).takeIf { open }
    }

}
open class  Loot(val value: Int)

class Fedora(val name: String, value: Int): Loot(value)

class Coin(value: Int): Loot(value)

class Barrel<out T>(val item: T)

fun main(){
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("a generic-looking fedora",15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(15))
    val lootBox: LootBox<Fedora> = LootBox(Fedora("a generic-looking fedora",15),
                                           Fedora("a dazzling magenta fedora",25))

    val lootBox2: LootBox<Coin> = LootBox(Coin(15))

    lootBox.open = true
    lootBox.fetch(0)?.run {
        println("You retreive $name from the box!")
    }

    val coin = lootBox.fetch(0){
        Coin(it.value * 3)
    }
    coin?.let { println(it.value)}

    val fedora = lootBox[0]
    fedora?.let { print(it.name)}

    val myFedora: Fedora = fedoraBarrel.item
}