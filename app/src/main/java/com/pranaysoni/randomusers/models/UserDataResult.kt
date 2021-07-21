package com.pranaysoni.randomusers.models


import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.pranaysoni.randomusers.database.Converters

@Entity(tableName = "USER_DATA")
data class UserDataResult(
    @SerializedName("cell")
    val cell: String,
    @SerializedName("dob")
    @ColumnInfo(name = "dob") val dob: Dob,
    @PrimaryKey @SerializedName("email")
    @ColumnInfo(name = "email") val email: String,
    @SerializedName("gender")
    @ColumnInfo(name = "gender")val gender: String,
    @SerializedName("name")
    @ColumnInfo(name = "name")val name: Name,
    @SerializedName("nat")
    val nat: String,
    @SerializedName("phone")
    val phone: String,
    @TypeConverters(Converters::class) @SerializedName("picture")
    @ColumnInfo(name = "picture")val picture: Picture
)