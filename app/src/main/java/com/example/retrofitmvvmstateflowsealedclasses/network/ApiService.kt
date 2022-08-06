package com.example.retrofitmvvmstateflowsealedclasses.network

import com.example.retrofitmvvmstateflowsealedclasses.data.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }


    @GET("users")
    suspend fun getUsers() : Response<UsersResponse>
}