package hu.autsoft.krate.moshi.test

import hu.autsoft.krate.moshi.MoshiTestKrate
import hu.autsoft.krate.moshi.TestModel
import hu.autsoft.krate.moshi.util.targetContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MoshiDelegateWithDefaultTest {

    private lateinit var krate: MoshiTestKrate

    @Before
    fun setup() {
        krate = MoshiTestKrate(targetContext)
    }

    @Test
    fun testInitialValues() {
        assertEquals(MoshiTestKrate.DEFAULT_SIMPLE_VALUE, krate.simpleValueWithDefault)
        assertEquals(MoshiTestKrate.DEFAULT_LIST_VALUE, krate.listOfValuesWithDefault)
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
