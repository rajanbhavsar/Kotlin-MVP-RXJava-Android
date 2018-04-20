package rajansinh.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import rajansinh.sttl.model.ResultData
@Dao
interface VideoDao {

    @Query("SELECT * from videoList")
    fun getAll(): MutableList<ResultData>

    @Insert(onConflict = REPLACE)
    fun insert(resultData: ResultData)

    @Query("DELETE from videoList")
    fun deleteAll()

    @Insert
    fun insertAll(resultData: MutableList<ResultData>)
}