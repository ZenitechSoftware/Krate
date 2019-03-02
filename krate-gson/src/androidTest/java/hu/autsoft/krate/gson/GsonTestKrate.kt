package hu.autsoft.krate.gson

import android.content.Context
import hu.autsoft.krate.SimpleKrate


class GsonTestKrate(context: Context) : SimpleKrate(context) {

    init {
        sharedPreferences.edit().clear().commit()
    }

    var simpleValue: TestModel? by gsonPref("simpleValue")
    var listOfValues: List<TestModel>? by gsonPref("listOfValues")

    companion object {
        val DEFAULT_SIMPLE_VALUE = TestModel(x = 1, y = 2.0, z = "3")
        val DEFAULT_LIST_VALUE = emptyList<TestModel>()
    }

    var simpleValueWithDefault: TestModel
            by gsonPref("simpleValueWithDefault", DEFAULT_SIMPLE_VALUE)

    var listOfValuesWithDefault: List<TestModel>
            by gsonPref("listOfValuesWithDefault", defaultValue = DEFAULT_LIST_VALUE)

}
