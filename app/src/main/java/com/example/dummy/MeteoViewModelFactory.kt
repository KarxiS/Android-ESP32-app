package com.example.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MeteoViewModelFactory(private val db: AppDatabase): ViewModelProvider.Factory {
    /**
     * factory na viewy, lebo mam zvlast konstruktor na db, zobrane z kurzu
     *
     * @param T
     * @param modelClass
     * @return
     */
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(MeteoViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MeteoViewModel(db) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }
}