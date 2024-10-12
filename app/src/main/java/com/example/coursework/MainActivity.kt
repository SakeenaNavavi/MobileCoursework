package com.example.coursework

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var siren: LinearLayout
    private lateinit var currentlocation: LinearLayout
    private lateinit var sos: LinearLayout
    private lateinit var news: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val backgroundService = Intent(applicationContext, ScreenOnOffBackgroundService::class.java)
        startService(backgroundService)
        Log.d(ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "Activity onCreate")

        val permissionCheck = ContextCompat.checkSelfPermission(
            this, Manifest.permission.SEND_SMS
        )

        if (permissionCheck != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            val alert = AlertDialog.Builder(this)
            val mView = layoutInflater.inflate(R.layout.custom_dialog_mainactivity, null)

            val btnOkay = mView.findViewById<Button>(R.id.btn_okay)
            val heading = mView.findViewById<TextView>(R.id.heading)
            heading.text = "SafeHer needs access to"

            val sms = mView.findViewById<TextView>(R.id.sms)
            sms.text = "Sending SMS:-"

            val textView = mView.findViewById<TextView>(R.id.textFormodal)
            textView.text = "Emergency messaging needs SEND SMS permission"

            val location = mView.findViewById<TextView>(R.id.location)
            location.text = "Location:-"

            val locationText = mView.findViewById<TextView>(R.id.textLocation)
            locationText.text = "Messaging embedded with live location needs Location permission"

            val call = mView.findViewById<TextView>(R.id.call)
            call.text = "Phone Call:-"

            val callText = mView.findViewById<TextView>(R.id.textCall)
            callText.text = "Emergency Calling needs CALL PHONE permission"

            val declaration = mView.findViewById<TextView>(R.id.declaration)
            declaration.text = "Declaration"

            val declarationText = mView.findViewById<TextView>(R.id.textDeclaration)
            declarationText.text =
                "The app is solely developed by Undergraduate Developers and all data related to this app is stored locally in your phone."

            val checkbox = mView.findViewById<CheckBox>(R.id.checkBox)
            val checkBoxText = mView.findViewById<TextView>(R.id.checkBoxText)

            checkbox.visibility = View.VISIBLE
            checkBoxText.visibility = View.VISIBLE

            checkbox.isEnabled = true
            checkBoxText.isEnabled = true

            checkBoxText.text = Html.fromHtml(
                "I accept the " +
                        "<a href='https://www.websitepolicies.com/policies/view/IaK4RjyB'>PRIVACY POLICY</a>" + " of the app"
            )
            checkBoxText.isClickable = true
            checkBoxText.movementMethod = LinkMovementMethod.getInstance()

            alert.setView(mView)
            val alertDialog = alert.create()
            alertDialog.setCanceledOnTouchOutside(false)

            btnOkay.setOnClickListener {
                if (checkbox.isChecked) {
                    alertDialog.dismiss()
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.SEND_SMS,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.CALL_PHONE
                        ), 0
                    )
                } else {
                    Toast.makeText(this, "Please accept privacy policy", Toast.LENGTH_LONG).show()
                }
            }
            alertDialog.show()
        }

        // Inflate the 'homepageuser' layout
        val homePageView = layoutInflater.inflate(R.layout.homepageuser, null)

        // Find views from 'homepageuser' layout
        siren = homePageView.findViewById(R.id.Siren)
        currentlocation = homePageView.findViewById(R.id.Currentlocation)
        sos = homePageView.findViewById(R.id.sos)
        news = homePageView.findViewById(R.id.news)

        siren.setOnClickListener {
            startActivity(Intent(applicationContext, Flashing::class.java))
        }

        sos.setOnClickListener {
            startActivity(Intent(applicationContext, SmsActivity::class.java))
        }


        news.setOnClickListener {
            startActivity(Intent(applicationContext, NewsActivity::class.java))
        }

    }
}