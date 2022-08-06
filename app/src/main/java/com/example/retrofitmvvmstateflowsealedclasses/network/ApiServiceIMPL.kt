package com.example.retrofitmvvmstateflowsealedclasses.network

import com.example.retrofitmvvmstateflowsealedclasses.data.UsersResponse
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import retrofit2.http.POST
import javax.inject.Inject

class ApiServiceIMPL @Inject constructor(private val apiService : ApiService){
    suspend fun getPost() : Response<UsersResponse> = apiService.getUsers()
}