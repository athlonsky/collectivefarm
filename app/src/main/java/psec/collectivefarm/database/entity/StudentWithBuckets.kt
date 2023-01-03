package psec.collectivefarm.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class StudentWithBuckets(
    @Embedded
    var student: Student,
    @Relation(parentColumn = "id", entityColumn = "studentId")
    var bucket: Bucket
)