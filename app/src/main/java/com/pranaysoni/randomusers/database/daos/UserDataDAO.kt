package com.pranaysoni.randomusers.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pranaysoni.randomusers.models.UserDataResult

@Dao
interface UserDataDAO {

    @Query("SELECT * FROM USER_DATA")
    suspend fun getAllUsers(): MutableList<UserDataResult>

    @Insert
    suspend fun updateNewUsersList(usersList: ArrayList<UserDataResult>)

    @Query("DELETE FROM USER_DATA")
    suspend fun deleteAllOldUser()

}