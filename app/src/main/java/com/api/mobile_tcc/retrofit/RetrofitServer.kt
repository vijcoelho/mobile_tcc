package com.api.mobile_tcc.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServer {

    private lateinit var retrofit: Retrofit

    constructor() {
        initializeRetrofit()
    }

    private fun initializeRetrofit() {
        val gson: Gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.15.15:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getRetrofit(): Retrofit {
        return retrofit
    }

    fun getAuthUser(): MyUserRetrofit {
        return retrofit.create(MyUserRetrofit::class.java)
    }
}