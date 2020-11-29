package hu.autsoft.krate.kotlinx.test

import hu.autsoft.krate.Krate
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.kotlinx.defaultJson
import hu.autsoft.krate.kotlinx.internalJson
import hu.autsoft.krate.kotlinx.json
import hu.autsoft.krate.targetContext
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CustomJsonInstanceTest {

    private lateinit var krate: Krate

    private fun createCustomJson(): Json {
        return Json {
            coerceInputValues = true
            encodeDefaults = true
            prettyPrint = true
        }
    }

    @Before
    fun setup() {
        krate = object : SimpleKrate(targetContext) {}
    }

    @Test
    fun testDefaultJsonInstanceInternal() {
        assertEquals(defaultJson, krate.internalJson)
    }

    @Test
    fun testCustomJsonInstanceInternal() {
        val customJson = createCustomJson()

        krate.json = customJson

        assertEquals(customJson, krate.internalJson)
    }

    @Test
    fun testMultipleJsonInstancesInternal() {
        val customJson = createCustomJson()
        krate.json = customJson
        assertEquals(customJson, krate.internalJson)

        val customJson2 = createCustomJson()
        krate.json = customJson2
        assertEquals(customJson2, krate.internalJson)

        krate.json = null
        assertEquals(defaultJson, krate.internalJson)
    }

    @Test
    fun testDefaultJsonInstancePublic() {
        assertEquals(null, krate.json)
    }

    @Test
    fun testCustomJsonInstancePublic() {
        val customJson = createCustomJson()

        krate.json = customJson

        assertEquals(customJson, krate.json)
    }

    @Test
    fun testMultipleJsonInstancesPublic() {
        val customJson = createCustomJson()
        krate.json = customJson
        assertEquals(customJson, krate.json)

        val customJson2 = createCustomJson()
        krate.json = customJson2
        assertEquals(customJson2, krate.json)

        krate.json = null
        assertEquals(null, krate.json)
    }

}
