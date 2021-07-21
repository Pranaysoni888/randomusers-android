package com.pranaysoni.randomusers.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pranaysoni.randomusers.models.Dob
import com.pranaysoni.randomusers.models.Name
import com.pranaysoni.randomusers.models.Picture

class Converters {

    @TypeConverter
    fun fromStringToPicture(value: String): Picture? {
        return Gson().fromJson(value,Picture::class.java)
    }

    @TypeConverter
    fun fromPictureToString(picture: Picture): String? {
        return Gson().toJson(picture)
    }


    @TypeConverter
    fun fromStringToName(value: String): Name? {
        return Gson().fromJson(value, Name::class.java)
    }

    @TypeConverter
    fun fromNameToString(name: Name): String? {
        return Gson().toJson(name)
    }


    @TypeConverter
    fun fromStringToDOB(value: String): Dob? {
        return Gson().fromJson(value, Dob::class.java)
    }

    @TypeConverter
    fun fromDOBToString(dob: Dob): String? {
        return Gson().toJson(dob)
    }
}