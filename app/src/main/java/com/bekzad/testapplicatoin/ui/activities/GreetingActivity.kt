package com.bekzad.testapplicatoin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.bekzad.testapplicatoin.R
import com.bekzad.testapplicatoin.ui.greetings.GreetingFragment

class GreetingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container, GreetingFragment.newInstance())
            }

        }
    }
}