package com.pranaysoni.randomusers.interfaces

import com.pranaysoni.randomusers.database.AppDatabase
import com.pranaysoni.randomusers.models.UserDataResult

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getAllUsers(): MutableList<UserDataResult> = appDatabase.userDataDAO().getAllUsers()
    override suspend fun updateNewUsersList(usersList: ArrayList<UserDataResult>) {
        appDatabase.userDataDAO().updateNewUsersList(usersList)
    }
    override suspend fun deleteAllOldUsers() {
        appDatabase.userDataDAO().deleteAllOldUser()
    }

}