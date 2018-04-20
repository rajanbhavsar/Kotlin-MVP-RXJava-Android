package rajansinh.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import rajansinh.sttl.model.ResultData

@Database(entities = arrayOf(ResultData::class), version = 1)
abstract class MyDatabase : RoomDatabase(){

    abstract fun VideoListDao(): VideoDao
}