import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.safeher.SettingsActivity
import com.example.coursework.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Find TextView elements by ID
        val homeTextView = findViewById<TextView>(R.id.text_home)
        val notificationsTextView = findViewById<TextView>(R.id.text_notifications)
        val settingsTextView = findViewById<TextView>(R.id.text_settings)

        // Set click listener for Settings
        settingsTextView.setOnClickListener {
            // Navigate to SettingsActivity
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}
