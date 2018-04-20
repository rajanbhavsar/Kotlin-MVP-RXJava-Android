package rajansinh

import android.app.Application
import android.arch.persistence.room.Room
import rajansinh.dao.MyDatabase


class MyApplication : Application() {

    companion object {
        var database: MyDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        MyApplication.database = Room.databaseBuilder(this, MyDatabase::class.java, "mydatabase.db").allowMainThreadQueries().build()
    }
}