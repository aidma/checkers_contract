import GameConverter.newGame
import GameConverter.print
import TableDrawer.draw

fun main(args: Array<String>) {

    playGame(
        listOf(
            "e3f4" to "f6g5",
            "f2e3" to "b6a5",
            "e3d4" to "g5e3c5",
            "g3f4" to "h6g5",
            "f4h6" to "a7b6",
            "a3b4" to "c5a3",
            "d2e3" to "g7f6",
            "e3d4" to "h8g7",
            "c1d2" to "a3c1e3c5",
            "e1d2" to "c5b4",
            "c3d4" to "b4c5",
            "g1f2" to "c5e3g1",
            "a1b2" to "g1f2",
            "b2c3" to "f2e1",
            "c3d4" to "e1c3e5",
            "h2g3" to "e5f4",
            "g3e5" to "d6f4"
        )
    )

    val white = Player("00")
    white.move("aa", "lose")
    println(GameStorage.load("00", "aa").currentPosition)

}

fun playGame(moves: List<Pair<String, String>>) {
    val white = Player("00")
    val black = Player("aa")
    moves.forEach {
        white.move(black.hash, it.first)
        println(GameStorage.load(white.hash, black.hash).table.draw())
        black.move(white.hash, it.second)
        println(GameStorage.load(black.hash, white.hash).table.draw())
    }
}


