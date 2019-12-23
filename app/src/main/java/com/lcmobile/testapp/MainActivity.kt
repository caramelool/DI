package com.lcmobile.testapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lcmobile.di.Injectable
import com.lcmobile.di.Module
import com.lcmobile.di.moduleOf
import java.lang.IllegalStateException

val mainModule = moduleOf {
    provide {
        "Main"
    }
}

class MainActivity : AppCompatActivity(), Injectable {

    override val module by mainModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class MainFragment : Fragment(), Injectable {

    override val module by mainModule

    override fun getApplicationContext(): Context {
        return activity?.applicationContext ?: throw IllegalStateException()
    }
}