package psec.collectivefarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import psec.collectivefarm.database.MainDB
import psec.collectivefarm.database.entity.Student

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val database = MainDB.getDB(application)

    val student: Flow<List<Student>> = database.getStudentsDao().getAllStudents()

    fun addStudent(student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.getStudentsDao().insertStudent(student)
            }
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.getStudentsDao().insertStudent(student)
            }
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.getStudentsDao().deleteStudent(student)
            }
        }
    }
}