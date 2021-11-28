import Game.Position.BLACK_TURN
import Game.Position.WHITE_TURN
import MoveCalculator.calculateMove

class Player(
    val hash: String
) {

    fun move(opponentHash: String, move: String) {
        val game = GameStorage.load(hash, opponentHash)
        checkPosition(game)

        if (move == "lose") {
            game.lose()
            return
        }

        val moves = parseMove(move)

        calculateMove(game, moves)
    }

    private fun checkPosition(game: Game) {
        assert(game.currentPosition != Game.Position.BLACK_WIN)
        assert(game.currentPosition != Game.Position.WHITE_WIN)
        assert(game.currentPosition != Game.Position.DRAW)

        if (game.currentPosition == WHITE_TURN)
            assert(hash == game.whitePlayerHash)

        if (game.currentPosition == BLACK_TURN)
            assert(hash == game.blackPlayerHash)
    }

    private fun parseMove(move: String): List<Sell> {
        assert(move.length.mod(2) == 0)

        return move
            .split("(?<=\\G.{2})".toRegex())
            .filter { it != "" }
            .map { SellConverter.fromString(it) }
    }

}


