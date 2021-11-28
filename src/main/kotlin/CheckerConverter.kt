import SellConverter.asByte
import SellConverter.asString

object CheckerConverter {

    fun Checker.asByte(): Int {
        val color = if (color) 0 else 1
        val king = if (isKing) 1 else 0

        return color * 128 + king * 64 + sell.asByte()
    }

    fun Checker.asString(): String {
        return "${if (color) "WHITE" else "BLACK"} ${if (isKing) "king" else ""} ${sell.asString()}"
    }

    fun fromByte(byte: Int): Checker {
        assert(byte >= 0)
        assert(byte < 256)
        val color = byte.div(128) == 0
        val king = byte.mod(128).div(64) == 1
        val sell = SellConverter.fromByte(byte.mod(64))

        return Checker(
            color,
            king,
            sell
        )
    }

    fun fromParams(color: Boolean, isKing: Boolean, position: String): Checker {
        val sell = SellConverter.fromString(position)

        return Checker(
            color,
            isKing,
            sell
        )
    }

}
