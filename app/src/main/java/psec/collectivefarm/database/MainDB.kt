package psec.collectivefarm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import psec.collectivefarm.database.dao.StudentDao
import psec.collectivefarm.database.entity.Bucket
import psec.collectivefarm.database.entity.Student

@Database(entities = [Bucket::class, Student::class], version = 2)
abstract class MainDB : RoomDatabase() {
    abstract fun getStudentsDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: MainDB? = null

        fun getDB(context: Context): MainDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MainDB::class.java,
                    "bd.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}