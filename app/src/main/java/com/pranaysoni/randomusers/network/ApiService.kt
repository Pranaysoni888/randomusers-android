package com.pranaysoni.randomusers.network

import com.pranaysoni.randomusers.models.UserModel
import retrofit2.Call
import retrofit2.http.GET

public interface ApiService {


    @GET("?results=100")
    fun gerUsersList(
    ): Call<UserModel>
}