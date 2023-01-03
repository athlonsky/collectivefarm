package psec.collectivefarm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import psec.collectivefarm.database.entity.Bucket
import psec.collectivefarm.database.entity.Student
import psec.collectivefarm.database.entity.StudentWithBuckets
import psec.collectivefarm.databinding.ActivityAddStudentBinding

class AddStudent : AppCompatActivity() {
    lateinit var binding: ActivityAddStudentBinding

    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(
            (this.application as MyApplication).database.getStudentsDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddStudent.setOnClickListener {
            val student = Student(
                name = binding.editTextName.text.toString(),
                lastName = binding.editTextLastName.text.toString(),
                middleName = binding.editTextMiddleName.text.toString(),
                group = binding.editTextGroup.text.toString(),
                loader = binding.checkBoxLoader.isChecked
            )
            val studentWithBuckets = StudentWithBuckets(student, Bucket())
            viewModel.addStudent(studentWithBuckets)
            Toast.makeText(this, "Студент был успешно добавлен!", Toast.LENGTH_SHORT).show()
        }
    }
}