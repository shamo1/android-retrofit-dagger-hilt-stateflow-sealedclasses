package com.example.retrofitmvvmstateflowsealedclasses.data

data class UsersResponseItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)