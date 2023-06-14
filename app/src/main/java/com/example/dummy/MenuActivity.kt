package com.example.dummy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    /**
     * nastavenie keylistennerov na buttony LED a Sensory
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val ledButton = findViewById<Button>(R.id.buttonLed)
        ledButton.setOnClickListener {
            val intent = Intent(this, LedActivity::class.java)
            startActivity(intent)
        }

        val sensorsButton = findViewById<Button>(R.id.buttonSensory)
        sensorsButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}