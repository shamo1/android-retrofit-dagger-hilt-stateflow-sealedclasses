package com.example.retrofitmvvmstateflowsealedclasses.repo

import com.example.retrofitmvvmstateflowsealedclasses.data.UsersResponse
import com.example.retrofitmvvmstateflowsealedclasses.network.ApiServiceIMPL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MainRepo @Inject constructor(private val api: ApiServiceIMPL) {

    fun getUsers(): Flow<Response<UsersResponse>> = flow {
        emit(api.getPost())
    }.flowOn(Dispatchers.IO)

}