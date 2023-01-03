package psec.collectivefarm.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import psec.collectivefarm.database.entity.Bucket
import psec.collectivefarm.database.entity.Student
import psec.collectivefarm.database.entity.StudentWithBuckets

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student): Long

    @Query("SELECT * FROM STUDENTS")
    fun getAllStudents(): Flow<List<Student>>

    @Transaction
    @Query("SELECT * FROM STUDENTS")
    fun getAllStudentsWithBuckets(): Flow<List<StudentWithBuckets>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBuckets(bucket: Bucket)

    @Transaction
    fun insertStudentWithBuckets(studentWithBuckets: StudentWithBuckets) {
        val studentId = insertStudent(studentWithBuckets.student)
        insertBuckets(studentWithBuckets.bucket.copy(studentId = studentId.toInt()))
    }

    @Delete
    fun deleteStudent(student: Student)
}