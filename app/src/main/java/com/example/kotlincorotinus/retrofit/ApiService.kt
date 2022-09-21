package com.example.kotlincorotinus.retrofit

import com.example.kotlincorotinus.models.Users
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id:Int) : Users
}