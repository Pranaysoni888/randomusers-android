package com.pranaysoni.randomusers.di.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pranaysoni.randomusers.R
import com.pranaysoni.randomusers.database.AppDatabase
import com.pranaysoni.randomusers.interfaces.DatabaseHelperImpl
import com.pranaysoni.randomusers.models.UserDataResult
import com.pranaysoni.randomusers.models.UserModel
import com.pranaysoni.randomusers.network.ApiService
import com.pranaysoni.randomusers.network.Resource
import com.pranaysoni.randomusers.network.RetrofitClientInstance
import com.pranaysoni.randomusers.utils.CommonUtils.checkInternetConnected
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeScreenRepository(private val context: Context) {

    val databaseHelper = DatabaseHelperImpl(AppDatabase.getInstance(context)!!)

    val authClient: ApiService by lazy {
        RetrofitClientInstance.createService(ApiService::class.java)
    }

    suspend fun getUsersList():MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        try {
            val usersListData = databaseHelper.getAllUsers()
            data.value = Resource.Success(usersListData)
            if(context.checkInternetConnected()){
                //data.value = Resource.Loading<Boolean>(true)
                val authClientCall = authClient.gerUsersList()
                authClientCall.enqueue(object : retrofit2.Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        data.value = Resource.Loading<Boolean>(false)

                        if (response.isSuccessful) {
                            val mBean: UserModel? = response.body()
                            data.value = Resource.Success(mBean?.results)
                            GlobalScope.launch {
                                databaseHelper.deleteAllOldUsers()
                                databaseHelper.updateNewUsersList(mBean?.results as ArrayList<UserDataResult>)
                            }
                        }
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        t.printStackTrace()
                        data.value = Resource.Error<String>(context.getString(R.string.msg_error_api_call))
                    }
                })
            }
        } catch (e: Exception) {
            data.value = Resource.Error<String>(e.message.toString())
        }
        return data
    }
}