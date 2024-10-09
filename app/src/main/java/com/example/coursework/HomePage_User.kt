package com.example.coursework

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HomePage_User : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepageuser)

        val helplineButton: LinearLayout = findViewById(R.id.helplineButton)
        val cameraButton: LinearLayout = findViewById(R.id.cameraButton)
        val locationButton: LinearLayout = findViewById(R.id.locationButton)
        val emergencyContactsButton: LinearLayout = findViewById(R.id.emergencyContactsButton)

        helplineButton.setOnClickListener {

        }

        cameraButton.setOnClickListener {

        }

        locationButton.setOnClickListener {

        }

        emergencyContactsButton.setOnClickListener {

            val intent = Intent(this, EmergencyContact::class.java)
            startActivity(intent)
        }
    }
}
