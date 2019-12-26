package com.lcmobile.di

fun parameterOf(vararg args: Any) = Parameter(args)

@Suppress("UNCHECKED_CAST")
class Parameter internal constructor(
    private val args: Array<out Any> = emptyArray()
) {
    operator fun <T> component1(): T {
        check(1)
        return args[0] as T
    }

    operator fun <T> component2(): T {
        check(2)
        return args[1] as T
    }

    operator fun <T> component3(): T {
        check(3)
        return args[2] as T
    }

    operator fun <T> component4(): T {
        check(4)
        return args[3] as T
    }

    operator fun <T> component5(): T {
        check(5)
        return args[4] as T
    }

    operator fun <T> component6(): T {
        check(6)
        return args[5] as T
    }

    private fun check(size: Int) {
        if (args.size < size)
            throw DIParameterException()
    }
}