package com.example.artbooktesting.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun List<Int>.listIntToJson(): String = gson.toJson(this)

    @TypeConverter
    fun String.toList(): List<Int> = gson.fromJson(this, object : TypeToken<List<Int>?>() {}.type)
}