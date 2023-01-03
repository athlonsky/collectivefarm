package psec.collectivefarm.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "buckets",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class Bucket(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var studentId: Int = 0,
    @ColumnInfo(name = "value")
    var value: Int = 0,
)