package psec.collectivefarm.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "students"
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "lastName")
    var lastName: String?,
    @ColumnInfo(name = "middleName")
    var middleName: String?,
    @ColumnInfo(name = "group")
    var group: String?,
    @ColumnInfo(name = "loader")
    var loader: Boolean?
)