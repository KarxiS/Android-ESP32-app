package com.example.dummy

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * deklaracia databazy, kvoli aktualizacii na cas pre graf, verzia2, niekedy treba migrovat
 *
 */
@Database(entities = [roomMeteo.MeteoStanica::class],
    version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun meteoStanicaDao(): MeteoStanicaDao


}