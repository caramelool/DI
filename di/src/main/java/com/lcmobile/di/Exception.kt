package com.lcmobile.di

import java.lang.RuntimeException

class DIException(message: String) : RuntimeException(message)

class DINotFoundException(
    className: String,
    name: String
) : RuntimeException("dependency not found for $className with name \"$name\"")

class DIMultiplesException(
    className: String,
    name: String
) : RuntimeException("multiples dependencies found for $className with name \"$name\"")

class DIParameterException(
    parameter: String
) : RuntimeException("parameter \"$parameter\" not found")