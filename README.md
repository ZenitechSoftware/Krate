# Krate

[![Build Status](https://app.bitrise.io/app/40d6bd22db4cfda8/status.svg?token=0neqv73n3TXp9F0nNxj_rA&branch=main)](https://app.bitrise.io/app/40d6bd22db4cfda8)

![Krate banner](./docs/krate.png)

**Krate** is a `SharedPreferences` wrapper library that uses delegated properties for convenient access to `SharedPreferences` values.

Here's what its basic usage looks like, extending the provided `SimpleKrate` class:

```kotlin
class UserSettings(context: Context) : SimpleKrate(context) {

    var notificationsEnabled by booleanPref("notifications_enabled").withDefault(false)
    var loginCount by intPref("login_count").withDefault(0)
    var nickname by stringPref("nickname")

}

val settings = UserSettings(context)
settings.loginCount = 10
Log.d("LOGIN_COUNT", "Count: ${settings.loginCount}")
```

# Dependency

Krate is available from `mavenCentral()`. You can add it to your dependencies with the following:

```groovy
implementation 'hu.autsoft:krate:1.2.0'
```

# Basics

A Krate property is nullable by default. It will have a `null` value if no value has been set for the property yet, and its current value can be erased from `SharedPreferences` completely by setting it to `null`.

```kotlin
var username: String? by stringPref("username")
```

### Default values

You can provide a default value for the property by chaining a `withDefault` call on the delegate function. This will give the property a non-nullable type.

```kotlin
var username: String by stringPref("username").withDefault("admin")
```

Reading from this property will return either the value it was last set to, or the default value if it's never been set.

> Note that there's no way to remove these values from `SharedPreferences` (although you could set it explicitly to the default value).

### Validation

You can add validation rules to your Krate properties by calling `validate` on any of Krate's delegate functions:

```kotlin
var percentage: Int by intPref("percentage")
    .withDefault(0)
    .validate { it in 0..100 }
```

If this validation fails, an `IllegalArgumentException` will be thrown.

# Custom Krate implementations

You can usually get away with extending `SimpleKrate`, as it does allow you to pass in a custom name for the `SharedPreferences` to be used to store your values in its constructor as an optional parameter. (If you pass in no `name` parameter to its constructor, it will default to using the instance returned by `PreferenceManager.getDefaultSharedPreferences(context)`.)  

However, you can also implement the `Krate` interface directly if you want to manage the `SharedPreferences` instance yourself for whatever reason - all this interface requires is a property that holds a `SharedPreferences` instance. With that, you can use the delegate functions the same way as shown above: 

```kotlin
class ExampleCustomKrate(context: Context) : Krate {

    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.applicationContext.getSharedPreferences("custom_krate_prefs", Context.MODE_PRIVATE)
    }

    var exampleBoolean by booleanPref("exampleBoolean").withDefault(false)
    
}
```

For simple applications, your `Activity` or `Fragment` can easily serve as a `Krate` implementation:

```kotlin
class MainActivity : AppCompatActivity(), Krate {

    override val sharedPreferences: SharedPreferences by lazy {
        getPreferences(Context.MODE_PRIVATE) // Could also fetch a named or default SharedPrefs
    }
    
    var username by stringPref("username").withDefault("")

}
```

### Third party implementations

You can create the `SharedPreferences` instance to implement Krate's interface by using third party `SharedPreferences` implementations as well. For example, [EncryptedSharedPreferences](https://developer.android.com/reference/androidx/security/crypto/EncryptedSharedPreferences) or [Harmony](https://github.com/pablobaxter/Harmony) are such implementations.

Here's how you'd use EncryptedSharedPreferences with Krate ([see this source file for the full code](app/src/main/java/hu/autsoft/krateexample/krates/ExampleThirdPartyKrates.kt)):

```kotlin
class EncryptedKrate(applicationContext: Context) : Krate {
    override val sharedPreferences: SharedPreferences

    init {
        /* ... */
        sharedPreferences = EncryptedSharedPreferences.create(applicationContext, ...)
    }

    val myStringValue: String by stringPref("my_string_value").withDefault("")
}
```

# Serialization addons

Krate, by default, supports the types that `SharedPreferences` supports. These are `Boolean`, `Float`, `Int`, `Long`, `String` and `Set<String>`. You may of course want to store additional types in Krate.

If you don't find support for the library or type you're looking for, implementing your own delegate in your own project based on the code of existing delegates should be quite simple, this is very much a supported use case. If you think your type might be commonly used, you can also open an issue to ask for an addon library for that type.

### Moshi support

This addon provides a `moshiPref` delegate which can store any arbitrary type, as long as Moshi can serialize and deserialize it.

Since Moshi supports both reflection-based serialization and code generation, there are multiple Krate artifacts for different use cases. *You should always include only one of the dependencies below, otherwise you'll end up with a dexing error.*

The usage of the Krate integration is the same for both setups:

```kotlin
class MoshiKrate(context: Context) : SimpleKrate(context) {
    var user: User? by moshiPref("user")
    var savedArticles: List<Article>? by moshiPref("articles")
}
```

If you want to provide your own `Moshi` instance that you've configured with your own custom adapters, you can set the `moshi` extension property on your Krate. Any `moshiPref` delegates within this Krate will use this instance for serialization and deserialization.

```kotlin
class CustomMoshiKrate(context: Context) : SimpleKrate(context) {
    init {
        moshi = Moshi.Builder().build()
    }
}
```

#### Only codegen

If you only want to use Moshi adapters that you generate via Moshi's [codegen facilities](https://github.com/square/moshi#codegen), you can use the following Krate artifact in your project to make use of these adapters: 

```groovy
implementation 'hu.autsoft:krate-moshi-codegen:1.2.0'
```

This will give you a default `Moshi` instance created by a call to `Moshi.Builder().build()`. This instance will find and use any of the adapters generated by Moshi's codegen automatically.

#### Only reflection, or mixed use of reflection and codegen

If you rely on [reflection](https://github.com/square/moshi#reflection) for your Moshi serialization, and therefore need a `KotlinJsonAdapterFactory` included in your `Moshi` instance, use the following Krate Moshi dependency:

```groovy
implementation 'hu.autsoft:krate-moshi-reflect:1.2.0'
```

The default `Moshi` instance from this dependency will include the aforementioned factory, and be able to serialize any Kotlin class. Note that this approach relies on the `kotlin-reflect` library, which is a large dependency.

You may choose to use Moshi's codegen for some classes in your project, and serialize the ones with no adapters generated with the default approach via reflection. For this mixed use case, you should also choose this dependency (unless you set your own custom Moshi instances as described above).

### Kotlinx.serialization support

The `krate-kotlinx` artifact provides a `kotlinxPref` delegate which can store any arbitrary type, as long as Kotlinx.serializazion can serialize and deserialize it. This addon, like the base library, is available from `mavenCentral()`:

```groovy
implementation 'hu.autsoft:krate-kotlinx:1.2.0'
```

Its usage is the same as with any of the base library's delegates:

```kotlin
class KotlinxKrate(context: Context) : SimpleKrate(context) {
    var user: User? by kotlinxPref("user")
    var savedArticles: List<Article>? by kotlinxPref("articles")
}
```

By default, the `Json.Default` is used. If you want to provide your own customized `Json` instance, you can set the `json` extension property on your Krate. Any `kotlinxPref` delegates within this Krate will use this instance for serialization and deserialization.

```kotlin
class CustomKotlinxKrate(context: Context) : SimpleKrate(context) {
    init {
        json = Json {
           coerceInputValues = true
           ...
        }
    }

    var user: User? by kotlinxPref("user")
}
```

### Gson support

The `krate-gson` artifact provides a `gsonPref` delegate which can store any arbitrary type, as long as Gson can serialize and deserialize it. This addon, like the base library, is available from `mavenCentral()`:

```groovy
implementation 'hu.autsoft:krate-gson:1.2.0'
```

Its basic usage is the same as with any of the base library's delegates:

```kotlin
class GsonKrate(context: Context) : SimpleKrate(context) {
    var user: User? by gsonPref("user")
    var savedArticles: List<Article>? by gsonPref("articles")
}
```

By default, the `Gson` instance created by a simple `Gson()` constructor call is used. If you want to provide your own `Gson` instance that you've configured, you can set the `gson` extension property on your Krate. Any `gsonPref` delegates within this Krate will use this instance for serialization and deserialization.

```kotlin
class CustomGsonKrate(context: Context) : SimpleKrate(context) {
    init {
        gson = GsonBuilder().create()
    }

    var user: User? by gsonPref("user")
}
```

# License

```
Copyright 2020 AutSoft

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
