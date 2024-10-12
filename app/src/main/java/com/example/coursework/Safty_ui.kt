package com.example.coursework
import DashboardActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Safty_ui : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.safty_ui)

        // Handle Dashboard Icon Click
        val dashboardIcon: ImageView = findViewById(R.id.dashboard_icon)
        dashboardIcon.setOnClickListener {
            // Navigate to DashboardActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        // Handle Button Click to Access Contacts
        val accessContactsButton: Button = findViewById(R.id.access_contacts_button)
        accessContactsButton.setOnClickListener {
            // Navigate to a new activity or perform the required action
            Toast.makeText(this, "Access Contacts Button Clicked", Toast.LENGTH_SHORT).show()

            // Example of navigating to a new activity (ContactsActivity)
            // val intent = Intent(this, ContactsActivity::class.java)
            // startActivity(intent)
        }
    }
}
