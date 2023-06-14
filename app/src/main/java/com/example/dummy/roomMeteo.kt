package com.example.dummy
import androidx.room.Entity
import androidx.room.PrimaryKey

class roomMeteo {

    /**
     * room databaza, deklarovanie tabuliek a jeho stlpcov
     *
     * @property id
     * @property name
     * @property temperature
     * @property humidity
     * @property time
     */
    @Entity(tableName = "meteoStanica")
    data class MeteoStanica(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val name:String,
        val temperature: Double,
        val humidity: Double,
        val time: Long,
    )

}