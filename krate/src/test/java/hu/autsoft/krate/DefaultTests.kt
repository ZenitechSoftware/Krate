package hu.autsoft.krate

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class DefaultTests {

    private lateinit var testKrate: TestKrate

    @Before
    fun setup() {
        testKrate = TestKrate(targetContext)
    }

    @Test
    fun testValidatedDefaultFloatDelegate() {
        testKrate.defaultValidatedFloat = .67f

        assertEquals(.67f, testKrate.defaultValidatedFloat)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testValidatedDefaultFloatDelegateThrowsException() {
        testKrate.defaultValidatedFloat = 5.0f
    }
}
