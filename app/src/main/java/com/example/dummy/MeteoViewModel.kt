package com.example.dummy

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MeteoViewModel(var db: AppDatabase) : ViewModel() {
    val meteoDataAll: LiveData<List<roomMeteo.MeteoStanica>> = db.meteoStanicaDao().getAll()
    val meteoLiveTemp = MutableLiveData<Double>()
    val meteoLiveHumidity = MutableLiveData<Double>()
    val meteoHighestTemp = MutableLiveData<Double>()
    val meteoLowestTemp = MutableLiveData<Double>()
    val meteoAvgTemp = MutableLiveData<Double>()


    init {
        meteoDataAll.observeForever { meteoData ->
            if (meteoData.isNotEmpty()) {
            meteoLiveTemp.value = meteoData.last().temperature
            meteoLiveHumidity.value = meteoData.last().humidity
            GlobalScope.launch(Dispatchers.IO) {
                meteoHighestTemp.postValue(db.meteoStanicaDao().getMaxTemp())
                meteoLowestTemp.postValue(db.meteoStanicaDao().getMinTemp())
                meteoAvgTemp.postValue(db.meteoStanicaDao().getAvg())
            }
            }
        }
    }
}
