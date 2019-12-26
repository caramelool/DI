package com.lcmobile.di

import android.content.Context

fun moduleOf(
    vararg modules: Lazy<Module> = emptyArray(),
    block: Module.() -> Unit
): Lazy<Module> = lazy { Module(modules.map { it.value }).apply(block) }

class Module internal constructor(arg: List<Module>) {

    val dependencies = mutableListOf<Dependency<*>>()

    init {
        arg.forEach {
            dependencies.addAll(it.dependencies)
        }
    }

    fun withContext(context: Context) = apply {
        if (dependencies.any { it.kClass == Context::class }.not()) {
            val dependency = Dependency(Context::class, "", true) { context }
            dependencies.add(dependency)
        }
    }

    inline fun <reified T : Any> provide(
        name: String = "",
        single: Boolean = false,
        noinline block: (Parameter) -> T
    ) {
        val dependency = Dependency(T::class, name, single, block)
        dependencies.add(dependency)
    }

    inline fun <reified T : Any> get(
        name: String = "",
        parameter: Parameter = parameterOf()
    ): T {
        val ref = dependencies
            .filter { it.kClass == T::class && it.name == name }
            .map { it.get(parameter) }
        if (ref.size > 1) {
            throw DIMultiplesException(T::class.java.name, name)
        }
        return ref.firstOrNull() as? T
            ?: throw DINotFoundException(T::class.java.name, name)
    }

}