package com.example.coursework

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.textfield.TextInputEditText
import java.io.IOException
import java.util.Locale

class SmsActivity : AppCompatActivity() {
    private lateinit var txt_pnumber1: TextInputEditText
    private lateinit var txt_msg: TextInputEditText
    private lateinit var txt_pnumber2: TextInputEditText
    private lateinit var txt_pnumber3: TextInputEditText
    private lateinit var txt_pnumber4: TextInputEditText
    private lateinit var Save: Button

    /**
     * LocationManager :- This class provides access to the system location services.
     * These services allow applications to obtain periodic updates of the device's geographical location,
     * or to be notified when the device enters the proximity of a given geographical location.
     */
    /**
     * FusedLocationProvider :- The fused location provider is one of the location APIs in Google Play services.
     * It manages the underlying location technology and provides a simple API so that you can specify
     * requirements at a high level, like high accuracy or low power.
     * It also optimizes the device's use of battery power.
     * The fused location provider  is used specifically to retrieve the device's last known location
     */
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var prevStarted: String = "yesSms"
    override fun onResume() {
        super.onResume()
        val sharedpreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prevStarted, java.lang.Boolean.TRUE)
            editor.apply()

            val alert = AlertDialog.Builder(this@SmsActivity)
            val mView: View = layoutInflater.inflate(R.layout.custom_dialog, null)

            val btn_okay = mView.findViewById<View>(R.id.btn_okay) as Button
            alert.setView(mView)
            val alertDialog = alert.create()
            alertDialog.setCanceledOnTouchOutside(false)
            btn_okay.setOnClickListener { alertDialog.dismiss() }
            alertDialog.show()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)

        txt_msg = findViewById<TextInputEditText>(R.id.txt_sms)

        txt_pnumber1 = findViewById<TextInputEditText>(R.id.txt_phone_number1)
        txt_pnumber2 = findViewById<TextInputEditText>(R.id.txt_phone_number2)
        txt_pnumber3 = findViewById<TextInputEditText>(R.id.txt_phone_number3)
        txt_pnumber4 = findViewById<TextInputEditText>(R.id.txt_phone_number4)
        Save = findViewById<Button>(R.id.Save_btn)

        // link :- https://google-developer-training.github.io/android-developer-advanced-course-concepts/unit-4-add-geo-features-to-your-apps/lesson-7-location/7-1-c-location-services/7-1-c-location-services.html
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)


        //saving data code demo
        Save.setOnClickListener(View.OnClickListener { //                v=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 400 milliseconds

            val phone1 = txt_pnumber1.getText().toString()
            val phone2 = txt_pnumber2.getText().toString()
            val phone3 = txt_pnumber3.getText().toString()
            val phone4 = txt_pnumber4.getText().toString()
            val msg = txt_msg.getText().toString()

            /**
             * SharedPreferences.Editor :- Interface used for modifying values in a SharedPreferences object.
             * All changes you make in an editor are batched, and not copied back to the original SharedPreferences
             * until you call commit() or apply().
             *
             * To write to a shared preferences file, SharedPreferences.Editor is created by calling edit() on the
             * SharedPreferences.
             */
            /**
             * SharedPreferences.Editor :- Interface used for modifying values in a SharedPreferences object.
             * All changes you make in an editor are batched, and not copied back to the original SharedPreferences
             * until you call commit() or apply().
             *
             * To write to a shared preferences file, SharedPreferences.Editor is created by calling edit() on the
             * SharedPreferences.
             */
            val shrd = getSharedPreferences("demo", MODE_PRIVATE)
            val editor = shrd.edit()
            editor.putString("phone1", phone1)
            editor.putString("phone2", phone2)
            editor.putString("phone3", phone3)
            editor.putString("phone4", phone4)
            editor.putString("msg", msg)
            editor.apply()
            txt_pnumber1.setText(phone1)
            txt_pnumber2.setText(phone2)
            txt_pnumber3.setText(phone3)
            txt_pnumber4.setText(phone4)
            txt_msg.setText(msg)
            if (txt_pnumber1.getText().toString() != "" && txt_pnumber2.getText()
                    .toString() != "" && txt_pnumber3.getText()
                    .toString() != "" && txt_pnumber4.getText().toString() != ""
            ) {
                Toast.makeText(this@SmsActivity, "Saved...", Toast.LENGTH_SHORT).show()
            } else if (txt_pnumber1.getText().toString() != "" || txt_pnumber2.getText()
                    .toString() != "" || txt_pnumber3.getText()
                    .toString() != "" || txt_pnumber4.getText().toString() != ""
            ) {
                Toast.makeText(
                    this@SmsActivity,
                    "Saved but some fields are blank...",
                    Toast.LENGTH_SHORT
                ).show()
            } else Toast.makeText(
                this@SmsActivity,
                "Please enter the numbers first...",
                Toast.LENGTH_SHORT
            ).show()
        })

        /**
         * SharedPreferences :- Android provides many ways of storing data of an application, one of the way is called as shared preference.
         * SharedPreferences object help you save simple application data as a name/value pairs - you specify a name for the data you want to save,
         * and then both it and its value will be saved automatically to an XML file for you. when you will close the application and then open it again then,
         * you will find these values as it was before closing the app , means they will be retrived.
         *
         * link :- https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
         * link :- https://www.youtube.com/watch?v=LHaxq3YdxZU&t=1s
         */
        /**
         * getSharedPreferences() :- Shared Preferences allow you to save and retrieve data in the form of key,value pair.
         * In order to use shared preferences, you have to call a method getSharedPreferences() that returns a SharedPreference
         * instance pointing to the file that contains the values of preferences.
         */

        //Getting the value of shared preference back
        val getShared = getSharedPreferences("demo", MODE_PRIVATE)
        val Value1 = getShared.getString("phone1", "")
        txt_pnumber1.setText(Value1)
        val Value2 = getShared.getString("phone2", "")
        txt_pnumber2.setText(Value2)
        val Value3 = getShared.getString("phone3", "")
        txt_pnumber3.setText(Value3)
        val Value4 = getShared.getString("phone4", "")
        txt_pnumber4.setText(Value4)
        val Value = getShared.getString("msg", "I am in danger, please come fast...")
        txt_msg.setText(Value)


        //saving data code demo ends here
    }


    fun btn_send(view: View?) {
        tryIt()
    }

    fun tryIt() {
        if (txt_pnumber1!!.text.toString().trim { it <= ' ' } != "") {
            /**
             * ContextCompat :- It is a class for replacing some work with base context.
             * For example if you used before something like getContext().getColor(R.color.black);
             * Now its deprecated since android 6.0 (API 22+) so you should use: getContext().getColor(R.color.black,theme);
             */
            if (ContextCompat.checkSelfPermission(
                    this@SmsActivity,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                makeCall()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
        } else {
            Toast.makeText(this, "Please enter first number...", Toast.LENGTH_LONG)
        }
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(
                    this@SmsActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                SendLocationMessage()
            } else {
                /**
                 * ActivityCompat :- public interface ActivityCompat. A helper for accessing features in Activity in a backwards
                 * compatible fashion. Construct this by using getActivityCompat(Activity) .
                 * This helper augments the included methods with data on instant apps.
                 */

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    44
                )
            }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 0)
        }
    }

    //Calling function
    private fun makeCall() {
        val intent = Intent(Intent.ACTION_CALL)
        val phoneNumber = txt_pnumber1!!.text.toString()
        if (phoneNumber.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(
                this@SmsActivity,
                "Please enter first number to make a call..",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            intent.setData(Uri.parse("tel:$phoneNumber"))
        }
        startActivity(intent)
    }

    private fun SendLocationMessage() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient!!.lastLocation.addOnCompleteListener { task ->
            //Initialize Location
            val location = task.result
            var Message: String? = txt_msg!!.text.toString().trim { it <= ' ' }
            if (location != null) {
                try {
                    // Read about Geocoder by just hover on where it is written
                    //Initialize Geocoder
                    val geocoder =
                        Geocoder(this@SmsActivity, Locale.getDefault())
                    //Initialize address list
                    val addresses = geocoder.getFromLocation(
                        location.latitude, location.longitude, 1
                    )
                    Message += "I am at " + addresses!![0].latitude +
                            "," + addresses[0].longitude + ", " + addresses[0].countryName +
                            "," + addresses[0].locality + ", " + addresses[0].getAddressLine(0)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                val str =
                    "Software was not able to retrieve live location due to some internal errors.."
                Message += str
            }
            val phoneNumber1 = txt_pnumber1!!.text.toString().trim { it <= ' ' }
            val phoneNumber2 = txt_pnumber2!!.text.toString().trim { it <= ' ' }
            val phoneNumber3 = txt_pnumber3!!.text.toString().trim { it <= ' ' }
            val phoneNumber4 = txt_pnumber4!!.text.toString().trim { it <= ' ' }
            if (txt_pnumber1!!.text.toString() != "" || txt_pnumber2!!.text.toString() != "" || txt_pnumber3!!.text.toString() != "" || txt_pnumber4!!.text.toString() != "") {
                if (txt_pnumber1!!.text.toString() != "") {
                    // SmsManager :- Manages SMS operations such as sending data, text, and pdu SMS messages.
                    // Get this object by calling the static method getDefault().

                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(phoneNumber1, null, Message, null, null)
                    Toast.makeText(
                        this@SmsActivity,
                        "Message sent...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (txt_pnumber2!!.text.toString() != "") {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(phoneNumber2, null, Message, null, null)
                    Toast.makeText(
                        this@SmsActivity,
                        "Message sent...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (txt_pnumber3!!.text.toString() != "") {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(phoneNumber3, null, Message, null, null)
                    Toast.makeText(
                        this@SmsActivity,
                        "Message sent...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (txt_pnumber4!!.text.toString() != "") {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(phoneNumber4, null, Message, null, null)
                    Toast.makeText(
                        this@SmsActivity,
                        "Message sent...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@SmsActivity,
                    "Please give the phone number first...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0 -> if (grantResults.size >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "You don't have required permissions", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    // main menu means logout button has created in actionbar.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.instructions_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_Detailed_instructions -> {
                startActivity(Intent(applicationContext, SosInsructionsActivity::class.java))
                return true
            }

            R.id.action_Short_instructions -> {
                val alert = AlertDialog.Builder(this@SmsActivity)
                val mView: View = layoutInflater.inflate(R.layout.custom_dialog, null)

                val btn_okay = mView.findViewById<View>(R.id.btn_okay) as Button
                alert.setView(mView)
                val alertDialog = alert.create()
                alertDialog.setCanceledOnTouchOutside(false)
                btn_okay.setOnClickListener { alertDialog.dismiss() }
                alertDialog.show()
                return false
            }

            else -> return false

        }
    }

    companion object {
        var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // internet lost alert dialog method call from here...
            }
        }
    }
}