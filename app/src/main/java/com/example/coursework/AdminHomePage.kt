package com.example.coursework

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class AdminHomePage : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adminhomepage)

//test
        val adminListButton: LinearLayout = findViewById(R.id.adminListButton)
        val addAdminButton: LinearLayout = findViewById(R.id.addAdminButton)



        adminListButton.setOnClickListener {

        }

        addAdminButton.setOnClickListener {

        }
    }
}
