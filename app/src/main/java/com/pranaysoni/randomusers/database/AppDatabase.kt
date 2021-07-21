package com.pranaysoni.randomusers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pranaysoni.randomusers.database.daos.UserDataDAO
import com.pranaysoni.randomusers.models.Dob
import com.pranaysoni.randomusers.models.Name
import com.pranaysoni.randomusers.models.Picture
import com.pranaysoni.randomusers.models.UserDataResult

@Database(entities = arrayOf(UserDataResult::class, Dob::class, Name::class, Picture::class),version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    companion object{
        private var APPDATABASE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (APPDATABASE == null) {
                synchronized(AppDatabase::class) {
                    APPDATABASE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase::class.java, "USER_DATA.db").allowMainThreadQueries()
                        .build()
                }
            }
            return APPDATABASE
        }
    }
    abstract fun userDataDAO(): UserDataDAO
}