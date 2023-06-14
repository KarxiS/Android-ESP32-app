package com.example.dummy
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeteoStanicaDao {
    @Insert
    fun insert(meteoStanica: roomMeteo.MeteoStanica)

    @Query("SELECT * FROM meteoStanica")
    fun getAll(): LiveData<List<roomMeteo.MeteoStanica>>

    @Query("SELECT * FROM meteoStanica ORDER BY id DESC LIMIT 1")
    fun getLast(): roomMeteo.MeteoStanica

    @Query("SELECT round(AVG(temperature),2) FROM meteoStanica")
    fun getAvg(): Double

    @Query("SELECT min(temperature) FROM meteoStanica")
    fun getMinTemp(): Double

    @Query("SELECT max(temperature) FROM meteoStanica")
    fun getMaxTemp(): Double


    @Query("DELETE FROM meteoStanica")
    fun deleteAll()


}