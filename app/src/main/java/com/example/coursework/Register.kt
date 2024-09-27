package com.example.coursework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Register :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)


            insets
        }


        val firstButton: Button = findViewById(R.id.RegisterbuttonNext)
        firstButton.setOnClickListener {
            startActivity(Intent(this@Register, MainActivity::class.java))
            finish()
        }
        val registerLink: TextView = findViewById(R.id.RegisterLink)
        registerLink.setOnClickListener {
            // Start the Register activity
            startActivity(Intent(this@Register, Login::class.java))
            finish() // Optional: Close the Login activity
        }

    }
}
