import CheckerConverter.fromByte
import java.math.BigInteger

class Table(
    val checkers: ArrayList<Checker>,
) {

    fun get(sell: Sell): Checker? =
        checkers.find {
            it.sell == sell
        }

    fun move(checker: Checker, sell: Sell) {
        checkers.remove(checker)
        val king = checker.isKing
                || (checker.color && sell.h == 7)
                || (!checker.color && sell.h == 0)

        checkers.add(Checker(checker.color, king, sell))
    }

    fun remove(checker: Checker) {
        checkers.remove(checker)
    }

    fun isEmpty(sell: Sell) = get(sell) == null

    companion object {
        private val DEFAULT_START = listOf(
            // 1-1-111-111
            // color-king-w-h
            //white
            "000", "020", "040", "060",
            "011", "031", "051", "071",
            "002", "022", "042", "062",
            //black
            "215", "235", "255", "275",
            "206", "226", "246", "266",
            "217", "237", "257", "277"
        ).map { BigInteger(it, 8).toInt() }

        fun fromBytes(bytes: List<Int>) =
            Table(bytes.map { fromByte(it) } as ArrayList<Checker>)

        fun defaultStart() = fromBytes(DEFAULT_START)
    }
}
