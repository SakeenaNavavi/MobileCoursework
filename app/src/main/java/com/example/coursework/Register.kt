package com.example.coursework
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()


        val fullNameField: EditText = findViewById(R.id.RegistereditTextFullName)
        val emailField: EditText = findViewById(R.id.RegistereditTextEmail)
        val passwordField: EditText = findViewById(R.id.RegistereditTextPassword)
        val registerButton: Button = findViewById(R.id.RegisterbuttonRegister)

        registerButton.setOnClickListener {
            val fullName = fullNameField.text.toString()
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            if (fullName.isEmpty()) {
                showAlertForFullName()
            } else if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            user?.let {
                                val userId = it.uid
                                val database = FirebaseDatabase.getInstance().reference
                                val userMap = mapOf(
                                    "fullName" to fullName,
                                    "email" to email,
                                    "password" to password
                                )
                                database.child("Users").child(userId).setValue(userMap)
                                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                                // Optionally, navigate to the main activity or another screen after registration
                            }
                        } else {
                            Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showAlertForFullName() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Full Name Required")
        builder.setMessage("Please enter your full name to register.")

        // Set up the input
        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, _ ->
            val fullNameInput = input.text.toString()
            if (fullNameInput.isNotEmpty()) {
                val email = findViewById<EditText>(R.id.RegistereditTextEmail).text.toString()
                val password = findViewById<EditText>(R.id.RegistereditTextPassword).text.toString()

                // Register the user with the provided name
                registerUser(fullNameInput, email, password)
            } else {
                Toast.makeText(this, "Full name cannot be empty", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun registerUser(fullName: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val database = FirebaseDatabase.getInstance().reference
                        val userMap = mapOf(
                            "fullName" to fullName,
                            "email" to email,
                            "password" to password
                        )
                        database.child("Users").child(userId).setValue(userMap)
                        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                        // Optionally, navigate to the main activity or another screen after registration
                    }
                } else {
                    Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        val registerLink: TextView = findViewById(R.id.RegisterLink)
        registerLink.setOnClickListener {

            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }

    }
}