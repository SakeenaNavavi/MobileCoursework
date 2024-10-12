package com.example.coursework

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AdminList : AppCompatActivity() {

    private lateinit var nameB1: TextView
    private lateinit var nameB2: TextView
    private lateinit var nameB3: TextView
    private lateinit var nameB4: TextView
    private lateinit var nameB5: TextView
    private lateinit var db: FirebaseFirestore

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adminlist)

        nameB1 = findViewById(R.id.nameB1)
        nameB2 = findViewById(R.id.nameB2)
        nameB3 = findViewById(R.id.nameB3)
        nameB4 = findViewById(R.id.nameB4)
        nameB5 = findViewById(R.id.nameB5)


        db = FirebaseFirestore.getInstance()

        fetchAdminListFromFirestore()
    }

    private fun fetchAdminListFromFirestore() {
        db.collection("admins")
            .get()
            .addOnSuccessListener { result ->
                val adminList = ArrayList<String>()
                for (document in result) {
                    val name = document.getString("name") ?: ""
                    adminList.add(name)
                }
                populateAdminList(adminList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error fetching admins: ${exception.message}", Toast.LENGTH_SHORT).show()
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
