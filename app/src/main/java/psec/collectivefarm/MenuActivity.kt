package psec.collectivefarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    private var recordOfWork: Button? = null
    var activityAddStudent: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        activityAddStudent = findViewById<View>(R.id.buttonAddStudent) as Button
        activityAddStudent!!.setOnClickListener(this)
        recordOfWork = findViewById<View>(R.id.buttonRecordOfWork) as Button
        recordOfWork!!.setOnClickListener(this)

    }
    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonRecordOfWork -> startActivity(Intent(this, RecordOfWork::class.java))
            R.id.buttonAddStudent -> startActivity(Intent(this, AddStudent::class.java))
        }
    }
}