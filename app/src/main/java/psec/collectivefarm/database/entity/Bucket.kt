package psec.collectivefarm.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buckets")
data class Bucket(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var studentId: Int,
    @ColumnInfo(name = "value")
    var value: Int? = null,
)