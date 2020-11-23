package com.example.noteapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConfig {
    private const val BASE_URL="http://192.168.43.168:8080"
    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val retrofit = builder.build()
    val apiService: NoteApi = retrofit.create(NoteApi::class.java)
}