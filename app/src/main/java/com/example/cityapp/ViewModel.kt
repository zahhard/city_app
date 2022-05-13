package com.example.cityapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.ArrayList


public class MainViewModel constructor(val app: Application) : AndroidViewModel(app) {

    var listForFragmentTwo = ArrayList<City>()

    fun findSaved() : ArrayList<City>{
        var listForFragmentTwo = ArrayList<City>()

        for (city in  CityRepository.city){
            if (city.isSelected)
                listForFragmentTwo.add(city)
        }
        return listForFragmentTwo
    }

}