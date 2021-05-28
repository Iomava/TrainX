package com.flamecode.trainx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flamecode.trainx.fragments.IntroFragment
import com.flamecode.trainx.manager.moveTo

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        moveTo(IntroFragment(), supportFragmentManager)
    }
}