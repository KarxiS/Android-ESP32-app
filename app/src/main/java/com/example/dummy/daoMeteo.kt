package com.example.dummy
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeteoStanicaDao {
    @Insert
    fun insert(meteoStanica: roomMeteo.MeteoStanica)

    @Query("SELECT * FROM meteoStanica")
    fun getAll(): MutableList<roomMeteo.MeteoStanica>

    @Query("SELECT * FROM meteoStanica ORDER BY id DESC LIMIT 1")
    fun getLast(): roomMeteo.MeteoStanica


}