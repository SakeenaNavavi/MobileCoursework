package com.example.coursework

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddLocation : AppCompatActivity() {

    private lateinit var timeFrom: EditText
    private lateinit var timeTo: EditText
    private lateinit var messageInput: EditText
    private lateinit var explafeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addlocaion)

        timeFrom = findViewById(R.id.time_from)
        timeTo = findViewById(R.id.time_to)
        messageInput = findViewById(R.id.message_input)
        explafeButton = findViewById(R.id.explafe_button)

        // Set up DatePickerDialog for Time From and Time To
        timeFrom.setOnClickListener { showDatePicker(timeFrom) }
        timeTo.setOnClickListener { showDatePicker(timeTo) }

        // Button click event
        explafeButton.setOnClickListener {
            val fromTime = timeFrom.text.toString()
            val toTime = timeTo.text.toString()
            val message = messageInput.text.toString()

            // Handle the input, for example sending the data or processing it
            println("From: $fromTime, To: $toTime, Message: $message")
        }
    }

    // Show DatePickerDialog
    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                editText.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}