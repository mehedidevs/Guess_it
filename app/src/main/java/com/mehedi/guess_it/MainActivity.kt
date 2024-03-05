

package com.mehedi.guess_it

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mehedi.guess_it.R

/**
 * Creates an Activity that hosts all of the fragments in the app
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

}
