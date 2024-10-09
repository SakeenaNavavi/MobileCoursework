package com.example.coursework

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewAddAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newaddadmin)


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

        }

        addButton.setOnClickListener {

            val name = nameBoxTextView.text.toString()
            val email = emailBoxTextView.text.toString()
            val phoneNo = phoneNoBoxTextView.text.toString()


        }
    }
}
