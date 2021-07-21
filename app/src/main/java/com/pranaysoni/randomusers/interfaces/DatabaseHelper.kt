package com.pranaysoni.randomusers.interfaces

import com.pranaysoni.randomusers.models.UserDataResult

interface DatabaseHelper {

    suspend fun getAllUsers(): MutableList<UserDataResult>

    suspend fun updateNewUsersList(usersList: ArrayList<UserDataResult>)

    suspend fun deleteAllOldUsers()
}