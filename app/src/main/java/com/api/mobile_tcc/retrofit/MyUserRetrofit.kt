package com.api.mobile_tcc.retrofit

import com.api.mobile_tcc.model.MyUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyUserRetrofit {

    @POST("/login")
    fun authenticatorUser(@Body myUser: MyUser): Call<ResponseBody>
}