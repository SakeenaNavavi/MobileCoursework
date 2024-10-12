package com.example.coursework
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_us)  // Your XML file layout

        // Access the back button from the layout
        val backButton: Button = findViewById(R.id.access_contacts_button)

        // Set an OnClickListener to handle back navigation
        backButton.setOnClickListener {
            // This will close the current About Us activity and go back to the previous screen
            finish()
        }
    }
}