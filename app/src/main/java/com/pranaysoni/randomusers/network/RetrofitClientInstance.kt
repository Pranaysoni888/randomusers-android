package com.pranaysoni.randomusers.network

import com.google.gson.GsonBuilder
import com.pranaysoni.randomusers.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClientInstance {

    private val BASE_URL = "https://randomuser.me/api/"

    fun getRetrofitInstance(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOKHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return retrofit
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return getRetrofitInstance().create(serviceClass)
    }

    fun getOKHttpClient():OkHttpClient{
        val builder = OkHttpClient().newBuilder()
            .authenticator(object : Authenticator {
                override fun authenticate(route: Route?, response: Response): Request? {
                    return response.request().newBuilder()
                        .header("Content-Type", "application/json")
                        .build()
                }

            })
            .connectTimeout(1, TimeUnit.MINUTES) // connection timeout
            .readTimeout(1, TimeUnit.MINUTES)    // read timeout
            .writeTimeout(1, TimeUnit.MINUTES)   // write timeout

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        return builder.build()
    }
}