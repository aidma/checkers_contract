object TableDrawer {

    fun Table.draw(): String {
        var result = ""
        result += "___a_b_c_d_e_f_g_h\n"
        for (h in 0..7) {
            result += "${h + 1} |"
            for (w in 0..7) {
                val char = this.get(Sell(w, h))?.toChar()
                result += "${char ?: '_'}|"
            }
            result += '\n'
        }
        return result
    }

    private fun Checker.toChar(): Char {
        val c = if (color) 'w' else 'b'
        return if (isKing)
            c.uppercaseChar()
        else
            c
    }
}
