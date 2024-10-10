package com.example.coursework

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class NewAddAdmin : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newaddadmin)

        db = FirebaseFirestore.getInstance() // Initialize Firestore

        //test comment

        val logoImageView: ImageView = findViewById(R.id.logo)
        val illustrationImageView: ImageView = findViewById(R.id.illustration)
        val titleTextView: TextView = findViewById(R.id.title)
        val nameTextView: TextView = findViewById(R.id.name)
        val nameBoxTextView: TextView = findViewById(R.id.nameBox)
        val emailTextView: TextView = findViewById(R.id.email)
        val emailBoxTextView: TextView = findViewById(R.id.emailBox)
        val phoneNoTextView: TextView = findViewById(R.id.phoneNo)
        val phoneNoBoxTextView: TextView = findViewById(R.id.phoneNoBox)
        val addButton: Button = findViewById(R.id.btnAdd)

        logoImageView.setImageResource(R.drawable.png_clipart_female_illustration_logo_woman_women_s_beauty_center_purple_face_thumbnail)
        logoImageView.setOnClickListener {
            // Handle logo click
        }

        addButton.setOnClickListener {
            val name = nameBoxTextView.text.toString()
            val email = emailBoxTextView.text.toString()
            val phoneNo = phoneNoBoxTextView.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && phoneNo.isNotEmpty()) {
                // Create a new admin entry
                val adminData = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "phoneNo" to phoneNo
                )

                // Add data to Firestore
                db.collection("admins")
                    .add(adminData)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Admin added successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e -> Toast.makeText(this, "Error adding admin: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
