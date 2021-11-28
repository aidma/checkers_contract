import CheckerConverter.asByte
import CheckerConverter.asString
import Game.Position.*
import java.math.BigInteger

object GameConverter {

    fun Game.print(
        pretty: Boolean = false
    ): String {
        var result = ""

        result += currentPosition.asInt
        if (pretty)
            result += " -> $currentPosition\n"

        result += whitePlayerHash
        if (pretty)
            result += " -> white player\n"

        result += blackPlayerHash
        if (pretty)
            result += " -> black player\n"

        table.checkers.forEach {
            result += it.asByte().as16String()

            if (pretty)
                result += " -> ${it.asString()}\n"
        }

        return result
    }


    fun newGame(
        whitePlayerHash: String,
        blackPlayerHash: String
    ) = Game(whitePlayerHash, blackPlayerHash, WHITE_TURN, Table.defaultStart())


    fun readFrom16String(string: String): Game {
        val position = findPosition(string[0].toString().asByte())
        val white = string.substring(1, 3)
        val black = string.substring(3, 5)
        val tableBytes = string.substring(5)
            .split("(?<=\\G.{2})".toRegex())
            .filter { it != "" }
            .map { it.asByte() }

        return Game(white, black, position, Table.fromBytes(tableBytes))
    }

    private fun String.asByte() = BigInteger(this, 16).toInt()
    private fun Int.as16String() = this.toString(16).padStart(2, '0')
    private fun findPosition(int: Int) = values().find { int == it.asInt } ?: throw AssertionError()

}
