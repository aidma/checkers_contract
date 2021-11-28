import Game.Position.*

data class Game(
    val whitePlayerHash: String,
    val blackPlayerHash: String,
    var currentPosition: Position,
    val table: Table
) {

    fun move(move: FullMove, finish: Boolean = true) {
        table.move(move.checker, move.to)
        if (move.eaten != null)
            table.remove(move.eaten)

        if (finish) finishMove()
    }

    fun lose() {
        if (currentPosition == WHITE_TURN)
            currentPosition = BLACK_WIN
        if (currentPosition == BLACK_TURN)
            currentPosition = WHITE_WIN
        GameStorage.save(this)
    }

    fun finishMove() {
        switchMove()
        GameStorage.save(this)
    }

    private fun switchMove() {
        currentPosition = when (currentPosition) {
            WHITE_TURN -> BLACK_TURN
            BLACK_TURN -> WHITE_TURN
            else -> throw AssertionError()
        }
    }

    fun currentColor(): Boolean {
        if (currentPosition == WHITE_TURN)
            return true
        if (currentPosition == BLACK_TURN)
            return false
        throw AssertionError()
    }

    enum class Position(val asInt: Int) {
        WHITE_TURN(0),
        BLACK_TURN(1),
        WHITE_WIN(2),
        BLACK_WIN(3),
        DRAW(4);
    }
}
