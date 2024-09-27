package com.example.coursework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val firstButton: Button = findViewById(R.id.LoginbuttonNext)
        firstButton.setOnClickListener {
            // Intent to navigate to another activity, for example
            startActivity(Intent(this@Login, Register::class.java))
            finish()
        }

        // Set up the register link
        val registerLink: TextView = findViewById(R.id.Loginlink)
        registerLink.setOnClickListener {
            // Start the Register activity
            startActivity(Intent(this@Login, Register::class.java))
            finish() // Optional: Close the Login activity
        }
    }
}
