package hu.autsoft.krate.gson.test

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import hu.autsoft.krate.gson.GsonTestKrate
import hu.autsoft.krate.gson.TestModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ValidatedTest {

    private lateinit var krate: GsonTestKrate

    @Before
    fun setup() {
        krate = GsonTestKrate(InstrumentationRegistry.getTargetContext())
    }

    @Test
    fun testValidValue() {
        val newValue = TestModel(20, 21.0, "")

        krate.validatedValue = newValue

        assertEquals(newValue, krate.validatedValue)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidValue() {
        krate.validatedValue = TestModel(10, 5.0, "")
    }

}
