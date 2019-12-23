package com.lcmobile.di

import android.content.Context
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private class Repository(val context: Context)

private val contextModule = moduleOf {

    provide {
        Repository(get())
    }

}

@RunWith(MockitoJUnitRunner::class)
class InjectContextTest : Injectable {

    @Mock
    lateinit var context: Context

    override val module by contextModule

    @Before
    fun before() {
        withContext(context)
    }

    @Test
    fun `test injection repository`() {
        val repo = get<Repository>()
        assertEquals(context, repo.context)
    }

}
