package psec.collectivefarm

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import psec.collectivefarm.database.MainDB
import psec.collectivefarm.database.dao.StudentDao
import psec.collectivefarm.database.entity.Student
import psec.collectivefarm.database.entity.StudentWithBuckets

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {

    val students: Flow<List<StudentWithBuckets>> =
        studentDao.getAllStudentsWithBuckets().onEach { println(it) }

    fun addStudent(studentWithBuckets: StudentWithBuckets) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.insertStudentWithBuckets(studentWithBuckets)
            }
        }
    }

    fun updateStudent(studentWithBuckets: StudentWithBuckets) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.insertStudentWithBuckets(studentWithBuckets)
            }
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                studentDao.deleteStudent(student)
            }
        }
    }
}

class StudentViewModelFactory(
    private val studentDao: StudentDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(studentDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}