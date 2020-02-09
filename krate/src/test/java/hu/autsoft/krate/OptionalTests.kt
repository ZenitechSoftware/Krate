package hu.autsoft.krate

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class OptionalTests {

    private lateinit var testKrate: TestKrate

    @Before
    fun setup() {
        testKrate = TestKrate(targetContext)
    }

    @Test
    fun testOptionalBooleanPref() {
        assertEquals(null, testKrate.optionalBoolean)

        testKrate.optionalBoolean = false
        assertEquals(false, testKrate.optionalBoolean)

        testKrate.optionalBoolean = true
        assertEquals(true, testKrate.optionalBoolean)

        testKrate.optionalBoolean = null
        assertEquals(null, testKrate.optionalBoolean)
    }

    @Test
    fun testOptionalFloatPref() {
        assertEquals(null, testKrate.optionalFloat)

        testKrate.optionalFloat = -487741f
        assertEquals(-487741f, testKrate.optionalFloat)

        testKrate.optionalFloat = 215125f
        assertEquals(215125f, testKrate.optionalFloat)

        testKrate.optionalFloat = null
        assertEquals(null, testKrate.optionalFloat)
    }

    @Test
    fun testOptionalIntPref() {
        assertEquals(null, testKrate.optionalInt)

        testKrate.optionalInt = -487741
        assertEquals(-487741, testKrate.optionalInt)

        testKrate.optionalInt = 215125
        assertEquals(215125, testKrate.optionalInt)

        testKrate.optionalInt = null
        assertEquals(null, testKrate.optionalInt)
    }

    @Test
    fun testOptionalLongPref() {
        assertEquals(null, testKrate.optionalLong)

        testKrate.optionalLong = -487741L
        assertEquals(-487741L, testKrate.optionalLong!!)

        testKrate.optionalLong = 215125L
        assertEquals(215125L, testKrate.optionalLong!!)

        testKrate.optionalLong = null
        assertEquals(null, testKrate.optionalLong)
    }

    @Test
    fun testOptionalStringPref() {
        assertEquals(null, testKrate.optionalString)

        testKrate.optionalString = "Lorem ipsum dolor sit amet"
        assertEquals("Lorem ipsum dolor sit amet", testKrate.optionalString)

        testKrate.optionalString = "Hello world!"
        assertEquals("Hello world!", testKrate.optionalString)

        testKrate.optionalString = null
        assertEquals(null, testKrate.optionalString)
    }

    @Test
    fun testOptionalStringSetPref() {
        assertEquals(null, testKrate.optionalStringSet)

        testKrate.optionalStringSet = setOf("Dog", "Cat")
        assertEquals(setOf("Dog", "Cat"), testKrate.optionalStringSet)

        testKrate.optionalStringSet = setOf("a, b, c, d")
        assertEquals(setOf("a, b, c, d"), testKrate.optionalStringSet)

        testKrate.optionalStringSet = null
        assertEquals(null, testKrate.optionalStringSet)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testOptionalStringValidatedPrefFailsValidation() {
        assertEquals(null, testKrate.optionalValidatedString)

        testKrate.optionalValidatedString = "Lorem ipsum dolor sit amet"
    }

    @Test
    fun testOptionalStringValidatedPassesValidation() {
        assertEquals(null, testKrate.optionalValidatedString)

        testKrate.optionalValidatedString = "Lorem"
    }

}
