package com.example.coursework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminList : AppCompatActivity() {
//test
    private lateinit var nameB1: TextView
    private lateinit var nameB2: TextView
    private lateinit var nameB3: TextView
    private lateinit var nameB4: TextView
    private lateinit var nameB5: TextView
    private lateinit var backButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adminlist)


        nameB1 = findViewById(R.id.nameB1)
        nameB2 = findViewById(R.id.nameB2)
        nameB3 = findViewById(R.id.nameB3)
        nameB4 = findViewById(R.id.nameB4)
        nameB5 = findViewById(R.id.nameB5)
        backButton = findViewById(R.id.btnAdd)


        val adminList = listOf(
            "Admin 1",
            "Admin 2",
            "Admin 3",
            "Admin 4",
            "Admin 5"
        )

        populateAdminList(adminList)


        backButton.setOnClickListener {

            val intent = Intent(this, AdminHomePage::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun populateAdminList(adminList: List<String>) {

        if (adminList.size > 0) nameB1.text = adminList[0]
        if (adminList.size > 1) nameB2.text = adminList[1]
        if (adminList.size > 2) nameB3.text = adminList[2]
        if (adminList.size > 3) nameB4.text = adminList[3]
        if (adminList.size > 4) nameB5.text = adminList[4]
    }
}
