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
class MoshiDelegateTest {

    private lateinit var krate: MoshiTestKrate

    @Before
    fun setup() {
        krate = MoshiTestKrate(targetContext)
    }

    @Test
    fun testInitialValues() {
        assertEquals(null, krate.simpleValue)
        assertEquals(null, krate.listOfValues)
    }

    @Test
    fun testSetSimpleValue() {
        val model = TestModel(x = 42, y = 3.141592, z = "shibboleth")

        krate.simpleValue = model

        assertEquals(model, krate.simpleValue)
    }

    @Test
    fun testRemoveSimpleValue() {
        val model = TestModel(x = 42, y = 3.141592, z = "shibboleth")

        krate.simpleValue = model
        krate.simpleValue = null

        assertEquals(null, krate.simpleValue)
    }

    @Test
    fun testSetListOfValues() {
        val list = listOf(
                TestModel(x = 42, y = 3.141592, z = "shibboleth"),
                TestModel(x = 13, y = 6.283185, z = "signal"),
                TestModel(x = 34, y = 2.718182, z = "bingo")
        )

        krate.listOfValues = list

        assertEquals(list, krate.listOfValues)
    }

    @Test
    fun testRemoveListOfValues() {
        val list = listOf(
                TestModel(x = 42, y = 3.141592, z = "shibboleth"),
                TestModel(x = 13, y = 6.283185, z = "signal"),
                TestModel(x = 34, y = 2.718182, z = "bingo")
        )

        krate.listOfValues = list
        krate.listOfValues = null

        assertEquals(null, krate.listOfValues)
    }

}
