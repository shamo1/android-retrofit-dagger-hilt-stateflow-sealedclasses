package com.example.retrofitmvvmstateflowsealedclasses.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvmstateflowsealedclasses.data.UsersResponse
import com.example.retrofitmvvmstateflowsealedclasses.repo.MainRepo
import com.example.retrofitmvvmstateflowsealedclasses.utils.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {


    /*MutableStateFlow*/
     val _userStateFlow: MutableStateFlow<ApiResponse<Response<UsersResponse>>> =
        MutableStateFlow(ApiResponse.INITIALSTATE)
    val userStateFlow: StateFlow<ApiResponse<Response<UsersResponse>>> get() = _userStateFlow


    fun getUser() = viewModelScope.launch {
        _userStateFlow.value = ApiResponse.Loading


        /*Checking Data Exception*/
        mainRepo.getUsers().catch { e ->
            _userStateFlow.value = ApiResponse.Failure(e.toString(), null)
        }.collect { data ->
            _userStateFlow.value = ApiResponse.Success(data)
        }

    }

}