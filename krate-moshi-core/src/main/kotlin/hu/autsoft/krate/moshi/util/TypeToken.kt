package hu.autsoft.krate.moshi.util

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@PublishedApi
internal abstract class TypeToken<T>

@PublishedApi
internal inline fun <reified T> makeType(): Type {
    val token = object : TypeToken<T>() {}

    val superclass: Type = requireNotNull(token::class.java.genericSuperclass)
    if (superclass is Class<*>) {
        throw RuntimeException("Missing type parameter.")
    }
    val parameterized = superclass as ParameterizedType
    return parameterized.actualTypeArguments[0]
}
