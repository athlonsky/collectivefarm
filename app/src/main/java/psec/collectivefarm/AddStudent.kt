package psec.collectivefarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import psec.collectivefarm.database.MainDB
import psec.collectivefarm.database.entity.Student
import psec.collectivefarm.databinding.ActivityAddStudentBinding

class AddStudent : AppCompatActivity() {
    lateinit var binding: ActivityAddStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDB.getDB(this)

        binding.buttonAddStudent.setOnClickListener {
            val student = Student(
                name = binding.editTextName.text.toString(),
                lastName = binding.editTextLastName.text.toString(),
                middleName = binding.editTextMiddleName.text.toString(),
                group = binding.editTextGroup.text.toString(),
                loader = binding.checkBoxLoader.isChecked
            )
            Thread {
                db.getStudentsDao().insertStudent(student)
            }.start()

        }
    }
}