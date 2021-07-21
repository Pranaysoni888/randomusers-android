package com.pranaysoni.randomusers.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "DOB")
data class Dob(
    @PrimaryKey val id:Int? = null,
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)