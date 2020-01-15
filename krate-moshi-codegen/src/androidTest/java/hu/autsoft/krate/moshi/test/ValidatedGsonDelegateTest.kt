package hu.autsoft.krate.moshi.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import hu.autsoft.krate.moshi.MoshiTestKrate
import hu.autsoft.krate.moshi.TestModel
import hu.autsoft.krate.moshi.moshi
import hu.autsoft.krate.moshi.util.targetContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidatedGsonDelegateTest {

    private lateinit var krate: MoshiTestKrate

    @Before
    fun setup() {
        krate = MoshiTestKrate(targetContext)

        krate.moshi = Moshi.Builder().build()

    }

    @Test
    fun testValidNonOptionalValue() {
        val newValue = TestModel(20, 21.0, "")

        krate.validatedValue = newValue

        assertEquals(newValue, krate.validatedValue)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidNonOptionalValue() {
        krate.validatedValue = TestModel(10, 5.0, "")
    }

    @Test
    fun testValidOptionalValue() {
        val newValue = listOf(
                TestModel(20, 21.0, ""),
                TestModel(10, 5.0, "")
        )

        krate.validatedOptionalValue = newValue

        assertEquals(newValue, krate.validatedOptionalValue)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidOptionalValue() {
        krate.validatedOptionalValue = null
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidOptionalValue2() {
        krate.validatedOptionalValue = emptyList()
    }

}
