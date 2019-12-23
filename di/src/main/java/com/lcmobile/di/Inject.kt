package com.lcmobile.di

import android.content.Context

interface Injectable {
    val module: Module
    fun withContext(context: Context) {
        module.withContext(context)
    }
}

inline fun <reified T : Any> Injectable.inject(
    name: String = "",
    parameter: Parameter = parameterOf()
): Lazy<T> = lazy { get<T>(name, parameter) }

inline fun <reified T : Any> Injectable.get(
    name: String = "",
    parameter: Parameter = parameterOf()
) : T {
    return module.get(name, parameter)
}