import java.awt.Color
import kotlin.math.abs

object MoveCalculator {

    fun calculateMove(game: Game, moves: List<Sell>) {
        var checker = extract(game, moves[0])

        if (!game.haveEatingMove(game.currentColor())) {
            assert(moves.size == 2)
            game.move(game.table.simple(checker, moves[1]))
            return
        }

        (1 until moves.size).forEach {
            game.move(game.table.eat(checker, moves[it]), false)
            checker = game.table.get(moves[it])!!
        }

        game.finishMove()
    }

    private fun extract(game: Game, sell: Sell): Checker {
        val checker = game.table.get(sell)
        assert(checker != null)
        if (game.currentPosition == Game.Position.WHITE_TURN)
            assert(checker!!.color)

        if (game.currentPosition == Game.Position.BLACK_TURN)
            assert(!checker!!.color)

        return checker!!
    }

    private fun Table.simple(checker: Checker, sell: Sell): FullMove {
        assert(this.isEmpty(sell))
        val dw = sell.w - checker.sell.w
        val dh = sell.h - checker.sell.h

        assert(abs(dw) == 1 && abs(dh) == 1)
        if (checker.isKing
            || checker.color && dh == 1
            || !checker.color && dh == -1
        ) return FullMove(checker, sell)

        throw AssertionError()
    }

    private fun Game.haveEatingMove(color: Boolean): Boolean {
        return this.table.checkers
            .filter { it.color == color }
            .map { any ->
                this.table.checkers
                    .filter { it.color != any.color }
                    .find { this.table.canEat(any, it) } != null
            }.find { it } != null
    }

    private fun Table.canEat(checker: Checker, other: Checker): Boolean {
        if (
            abs(checker.sell.w - other.sell.w) == 1
            && abs(checker.sell.h - other.sell.h) == 1
        ) {
            val newPosition =
                checker.sell.shift(2 * (other.sell.w - checker.sell.w), 2 * (other.sell.h - checker.sell.h))
            if (newPosition != null && this.isEmpty(newPosition))
                return true
        }
        return false
    }


    private fun Sell.shift(dw: Int, dh: Int): Sell? {
        val resw = this.w + dw
        val resh = this.h + dh

        return if (resw < 8 && resw > -1 && resh < 8 && resh > -1)
            Sell(resw, resh)
        else
            null
    }

    private fun Table.eat(checker: Checker, sell: Sell): FullMove {
        assert(
            abs(checker.sell.w - sell.w) == 2
                    && abs(checker.sell.h - sell.h) == 2
        )
        assert(this.isEmpty(sell))
        val eaten = this.get(Sell((checker.sell.w + sell.w) / 2, (checker.sell.h + sell.h) / 2))
        assert(eaten != null)

        return FullMove(checker, sell, eaten)
    }
}



