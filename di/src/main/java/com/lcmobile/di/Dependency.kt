package com.lcmobile.di

import kotlin.reflect.KClass

data class Dependency<T : Any>(
    val kClass: KClass<T>,
    val name: String,
    private val single: Boolean,
    private val block: Parameter.() -> T
) {
    private lateinit var ref: T
    fun get(parameter: Parameter): T {
        if (single) {
            if (::ref.isInitialized.not()) {
                ref = block(parameter)
            }
            return ref
        }
        return block(parameter)
    }

}