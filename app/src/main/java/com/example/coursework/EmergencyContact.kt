package com.example.coursework

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EmergencyContact : AppCompatActivity() {

    private lateinit var btnCallPolice: Button
    private lateinit var btnCallHospitals: Button
    private lateinit var btnFatherMother: Button
    private lateinit var btnGuardian: Button
    private lateinit var btnParents: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.emergencycontact)


        btnCallPolice = findViewById(R.id.btnCallPolice)
        btnCallHospitals = findViewById(R.id.btnCallHospitals)
        btnFatherMother = findViewById(R.id.btnFatherMother)
        btnGuardian = findViewById(R.id.btnGuardian)
        btnParents = findViewById(R.id.btnParents)


        btnCallPolice.setOnClickListener {

        }

        btnCallHospitals.setOnClickListener {

        }

        btnFatherMother.setOnClickListener {

        }

        btnGuardian.setOnClickListener {

        }

        btnParents.setOnClickListener {

        }
    }

}
