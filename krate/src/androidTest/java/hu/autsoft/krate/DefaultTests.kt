package hu.autsoft.krate

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DefaultTests {

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