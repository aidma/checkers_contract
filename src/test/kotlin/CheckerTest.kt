import CheckerConverter.fromByte
import CheckerConverter.fromParams
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CheckerTest {

    @Test
    fun creation() {
        assertEquals(fromByte(41).toString(), "WHITE  f2")
        assertEquals(fromByte(175).toString(), "BLACK  f8")

        assertEquals(fromParams(true, false, position = "f2").toString(), "WHITE  f2")
        assertEquals(fromParams(false, false, position = "f8").toString(), "BLACK  f8")
    }

    @Test
    fun impossiblePosition() {
        assertThrows(AssertionError::class.java)
        { fromParams(true, false, position = "f1") }
    }
}
