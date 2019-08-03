package hu.autsoft.krate

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DefaultTests {

    private lateinit var testKrate: TestKrate

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getTargetContext()
        testKrate = TestKrate(appContext)
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