package com.example.coursework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class NewAddAdmin : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newaddadmin)

        db = FirebaseFirestore.getInstance() // Initialize Firestore

        val userName: EditText = findViewById(R.id.nameBox)
        val Email: EditText = findViewById(R.id.emailBox)
        val Password: EditText = findViewById(R.id.phoneNoBox)
        val addButton: Button = findViewById(R.id.btnAdd)

        addButton.setOnClickListener {
            val name = userName.text.toString()
            val email = Email.text.toString()
            val Password = Password.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && Password.isNotEmpty()) {
                // Create a new admin entry

                val adminData = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "Password" to Password
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
