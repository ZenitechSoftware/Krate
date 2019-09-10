package hu.autsoft.krate.gson.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.autsoft.krate.gson.GsonTestKrate
import hu.autsoft.krate.gson.TestModel
import hu.autsoft.krate.targetContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GsonDelegateWithDefaultTest {

    private lateinit var krate: GsonTestKrate

    @Before
    fun setup() {
        krate = GsonTestKrate(targetContext)
    }

    @Test
    fun testInitialValues() {
        assertEquals(GsonTestKrate.DEFAULT_SIMPLE_VALUE, krate.simpleValueWithDefault)
        assertEquals(GsonTestKrate.DEFAULT_LIST_VALUE, krate.listOfValuesWithDefault)
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
