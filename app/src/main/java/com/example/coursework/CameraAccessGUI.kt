package com.example.coursework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class CameraAccessGUI :AppCompatActivity()  {override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_cameraaccess)


    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }

//This is a comment
    val firstButton: Button = findViewById(R.id.CamerabuttonNext)
    firstButton.setOnClickListener {
        val intent = Intent(this@CameraAccessGUI, LocationGUI::class.java)
        startActivity(intent)
        finish()
    }
}
    }