import GameConverter.print

object GameStorage {

    private val storage: HashMap<String, String> = hashMapOf()

    fun save(game: Game) {
        storage[game.whitePlayerHash + game.blackPlayerHash] = game.print(false)
    }

    fun load(first: String, second: String): Game {
        var s = storage[first + second] ?: storage[second + first]
        if (s == null) {
            save(GameConverter.newGame(first, second)) // TODO a lot of gas
            s = storage[first + second]
        }
        return GameConverter.readFrom16String(s!!)
    }

}
