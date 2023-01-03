package psec.collectivefarm

import android.app.Application
import psec.collectivefarm.database.MainDB

class MyApplication : Application() {
    val database: MainDB by lazy { MainDB.getDB(this) }
}