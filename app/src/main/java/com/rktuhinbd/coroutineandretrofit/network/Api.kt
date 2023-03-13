package com.rktuhinbd.coroutineandretrofit.network

import com.rktuhinbd.coroutineandretrofit.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}