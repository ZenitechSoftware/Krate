package hu.autsoft.krate.gson.test

import com.google.gson.GsonBuilder
import hu.autsoft.krate.Krate
import hu.autsoft.krate.SimpleKrate
import hu.autsoft.krate.gson.defaultGson
import hu.autsoft.krate.gson.gson
import hu.autsoft.krate.gson.internalGson
import hu.autsoft.krate.targetContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CustomGsonInstanceTest {

    private lateinit var krate: Krate

    @Before
    fun setup() {
        krate = object : SimpleKrate(targetContext) {}
    }

    @Test
    fun testDefaultGsonInstanceInternal() {
        assertEquals(defaultGson, krate.internalGson)
    }

    @Test
    fun testCustomGsonInstanceInternal() {
        val customGson = GsonBuilder().create()

        krate.gson = customGson

        assertEquals(customGson, krate.internalGson)
    }

    @Test
    fun testMultipleGsonInstancesInternal() {
        val customGson = GsonBuilder().create()
        krate.gson = customGson
        assertEquals(customGson, krate.internalGson)

        val customGson2 = GsonBuilder().create()
        krate.gson = customGson2
        assertEquals(customGson2, krate.internalGson)

        krate.gson = null
        assertEquals(defaultGson, krate.internalGson)
    }

    @Test
    fun testDefaultGsonInstancePublic() {
        assertEquals(null, krate.gson)
    }

    @Test
    fun testCustomGsonInstancePublic() {
        val customGson = GsonBuilder().create()

        krate.gson = customGson

        assertEquals(customGson, krate.gson)
    }

    @Test
    fun testMultipleGsonInstancesPublic() {
        val customGson = GsonBuilder().create()
        krate.gson = customGson
        assertEquals(customGson, krate.gson)

        val customGson2 = GsonBuilder().create()
        krate.gson = customGson2
        assertEquals(customGson2, krate.gson)

        krate.gson = null
        assertEquals(null, krate.gson)
    }

}
