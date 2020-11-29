package hu.autsoft.krate.kotlinx.test

import hu.autsoft.krate.kotlinx.KotlinxTestKrate
import hu.autsoft.krate.kotlinx.TestModel
import hu.autsoft.krate.targetContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class KotlinxDelegateWithDefaultTest {

    private lateinit var krate: KotlinxTestKrate

    @Before
    fun setup() {
        krate = KotlinxTestKrate(targetContext)
    }

    @Test
    fun testInitialValues() {
        assertEquals(KotlinxTestKrate.DEFAULT_SIMPLE_VALUE, krate.simpleValueWithDefault)
        assertEquals(KotlinxTestKrate.DEFAULT_LIST_VALUE, krate.listOfValuesWithDefault)
    }

    @Test
    fun testSimpleValue() {
        val model = TestModel(x = 42, y = 3.141592, z = "shibboleth")

        krate.simpleValueWithDefault = model

        assertEquals(model, krate.simpleValueWithDefault)
    }

    @Test
    fun testListOfValues() {
        val list = listOf(
                TestModel(x = 42, y = 3.141592, z = "shibboleth"),
                TestModel(x = 13, y = 6.283185, z = "signal"),
                TestModel(x = 34, y = 2.718182, z = "bingo")
        )

        krate.listOfValuesWithDefault = list

        assertEquals(list, krate.listOfValuesWithDefault)
    }

}
