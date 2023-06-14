package com.example.dummy

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [roomMeteo.MeteoStanica::class],
    version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun meteoStanicaDao(): MeteoStanicaDao


}