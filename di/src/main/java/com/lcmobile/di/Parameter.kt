package com.lcmobile.di

typealias Arg = Pair<String, Any>

fun parameterOf(vararg arg: Arg) = Parameter(arg)

class Parameter internal constructor(val arg: Array<out Arg> = emptyArray()) {
    inline fun <reified T> parameter(name: String): T {
        return arg
            .find { it.first == name }
            ?.second as? T
            ?: throw DIParameterException(name)
    }
}