package com.example.baseapp.base.network

import com.example.baseapp.model.SingleUser
import com.example.baseapp.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/users?page=2")
    suspend fun getUserList():Response<List<SingleUser>>

    @GET("/api/users/2")
    suspend fun getSingleUser():Response<User>
}