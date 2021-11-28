object SellConverter {
    private const val literals = "abcdefgh"

    fun Sell.asByte() = w * 8 + h
    fun Sell.asString() = "${literals[w]}${h + 1}"

    fun fromByte(byte: Int): Sell {
        assert(byte >= 0)
        assert(byte < 64)
        val w = byte.mod(64).div(8)
        val h = byte.mod(8)
        checkPosition(w, h)

        return Sell(w, h)
    }

    fun fromString(position: String): Sell {
        val w = literals.indexOf(position[0], ignoreCase = true)
        assert(w != -1)
        val h = Character.getNumericValue(position[1]) - 1
        checkPosition(w, h)

        return Sell(w, h)
    }

    private fun checkPosition(w: Int, h: Int) {
        assert(w in 0..7)
        assert(h in 0..7)
        assert((h + w).mod(2) == 0)
    }

}
