package com.lcmobile.di

import org.junit.Test

import org.junit.Assert.*

private class Single

private data class Scope(val name: String)

private val coreModule = moduleOf {
    provide {
        "Test"
    }

    provide {
        10f
    }
}

private val testModule = moduleOf(coreModule) {
    provide {
        10
    }

    provide {
        10f
    }

    provide(name = "scope") {
        val name = get<String>()
        val scope = parameter<String>("scope")
        "$name - $scope"
    }

    provide(single = true) {
        Single()
    }

    provide {
        val name = get<String>("scope", parameterOf("scope" to "scope"))
        Scope(name)
    }
}

class InjectTest : Injectable {

    override val module by testModule

    @Test
    fun `test injection String`() {
        val value = get<String>()
        assertEquals("Test", value)
    }

    @Test
    fun `test injection Int`() {
        val value = get<Int>()
        assertEquals(10, value)
    }

    @Test
    fun `test injection parameter`() {
        val param = "scope"
        val value = get<String>("scope", parameterOf("scope" to param))
        assertEquals("Test - $param", value)
    }

    @Test
    fun `test injection single`() {
        val single1 = get<Single>()
        val single2 = get<Single>()
        assertEquals(single1, single2)
    }

    @Test
    fun `test injection scope class`() {
        val scope = get<Scope>()
        assertEquals("Test - scope", scope.name)
    }

    @Test(expected = DIMultiplesException::class)
    fun `test injection multiples injection`() {
        get<Float>()
    }

    @Test(expected = DINotFoundException::class)
    fun `test injection dependency not found`() {
        class NotFound
        get<NotFound>()
    }

    @Test(expected = DIParameterException::class)
    fun `test injection parameter not found`() {
        get<String>("scope")
    }

}
