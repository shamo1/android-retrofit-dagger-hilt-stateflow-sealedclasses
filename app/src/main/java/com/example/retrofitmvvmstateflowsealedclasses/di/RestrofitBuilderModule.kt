package com.example.retrofitmvvmstateflowsealedclasses.di

import com.example.retrofitmvvmstateflowsealedclasses.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
object RestrofitBuilderModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesInterseptor(): OkHttpClient = OkHttpClient.Builder()/*.addInterceptor()*/
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS).build()


    @Provides
    @Singleton
    fun providesApiService(gson: Gson): ApiService {
        return Retrofit.Builder().run {
            baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }.create(ApiService::class.java)
    }


}