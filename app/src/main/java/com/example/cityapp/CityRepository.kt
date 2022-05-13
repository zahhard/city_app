package com.example.cityapp

import androidx.lifecycle.MutableLiveData

object CityRepository {
//    var cityList = ArrayList<String>()
    var city = ArrayList<City>()
    var NextFragmentCity = ArrayList<City>()

    fun init(){
        city.add(City("city1"))
        city.add(City("city2"))
        city.add(City("city3" ))
        city.add(City("city4"))
        city.add(City("city5"))
        city.add(City("city6"))
        city.add(City("city7"))
        city.add(City("city8"))
        city.add(City("city9"))
        city.add(City("city10"))
    }
}