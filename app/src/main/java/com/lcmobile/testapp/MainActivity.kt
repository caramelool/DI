package com.lcmobile.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lcmobile.di.Injectable
import com.lcmobile.di.moduleOf

val mainModule = moduleOf {
    provide {
        "Main"
    }
}

class MainActivity : AppCompatActivity(), Injectable {

    override val module by mainModule

    override fun onCreate(savedInstanceState: Bundle?) {
        withContext(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class MainFragment : Fragment(), Injectable {
    override val module by mainModule
}