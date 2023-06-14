package com.example.dummy
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeteoStanicaDao {
    /**
     * insert do databazy
     *
     * @param meteoStanica
     */
    @Insert
    fun insert(meteoStanica: roomMeteo.MeteoStanica)

    /**
     * vrati vsetky stanice z databazy
     *
     * @return
     */
    @Query("SELECT * FROM meteoStanica")
    fun getAll(): LiveData<List<roomMeteo.MeteoStanica>>

    /**
     * vrati poslednu vlozenu stanicu
     *
     * @return
     */
    @Query("SELECT * FROM meteoStanica ORDER BY id DESC LIMIT 1")
    fun getLast(): roomMeteo.MeteoStanica

    /**
     * vrati zaokruhleny priemer na 2 destat. miesta teplot vsetkych stanic
     *
     * @return
     */
    @Query("SELECT round(AVG(temperature),2) FROM meteoStanica")
    fun getAvg(): Double

    /**
     * vrati minimalnu zaznamenanu teplotu
     *
     * @return
     */
    @Query("SELECT min(temperature) FROM meteoStanica")
    fun getMinTemp(): Double

    /**
     * vrati max zaznamenanu teplotu
     *
     * @return
     */
    @Query("SELECT max(temperature) FROM meteoStanica")
    fun getMaxTemp(): Double

    /**
     * vymaze vsetky data do aktualneho casu , neimplementovane este TODO
     *
     * @param timeParam
     */
    @Query("DELETE FROM meteoStanica where time<:timeParam ")
    fun deleteUntilParam(timeParam:Long);

    /**
     * vytmaze celu databazu
     *
     */

    @Query("DELETE FROM meteoStanica")
    fun deleteAll()


}