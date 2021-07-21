package com.pranaysoni.randomusers.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "NAME")
data class Name(
    @PrimaryKey val id:Int? = null,
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
    @SerializedName("title")
    val title: String
)