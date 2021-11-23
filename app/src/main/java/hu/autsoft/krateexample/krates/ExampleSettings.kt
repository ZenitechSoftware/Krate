package hu.autsoft.krateexample.krates

import hu.autsoft.krateexample.models.User

interface ExampleSettings {
    var exampleBoolean: Boolean
    var exampleFloat: Float
    var exampleInt: Int
    var exampleLong: Long
    var exampleString: String
    var exampleStringSet: Set<String>
    var exampleUserGson: User
    var exampleUserKotlinX: User
    var exampleUserMoshi: User
}
