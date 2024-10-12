package com.example.coursework

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewAddAdmin : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newaddadmin)

        // Initialize Firebase Realtime Database
        dbRef = FirebaseDatabase.getInstance().getReference("admins")

        val userName: EditText = findViewById(R.id.nameBox)
        val email: EditText = findViewById(R.id.emailBox)
        val password: EditText = findViewById(R.id.phoneNoBox)
        val addButton: Button = findViewById(R.id.btnAdd)

        addButton.setOnClickListener {
            val name = userName.text.toString()
            val email = email.text.toString()
            val password = password.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Create a new admin entry

                val adminData = Admin(name, email, password)

                dbRef.push().setValue(adminData)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Admin added successfully to Realtime Database", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error adding admin: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Data model class
    data class Admin(val name: String, val email: String, val password: String)
}
