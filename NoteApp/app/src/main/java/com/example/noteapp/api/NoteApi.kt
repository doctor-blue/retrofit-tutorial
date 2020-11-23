package com.example.noteapp.api

import com.example.noteapp.data.Note
import retrofit2.http.*

interface NoteApi {

    @GET("/note")
    suspend fun getAllNote():List<Note>

    @POST("/note")
    suspend fun addNote(@Body note: Note): Note

    @DELETE("/note/{id}")
    suspend fun deleteNote(@Path("id") id: Int,): Note

    @PUT("/note/{id}")
    suspend fun updateNote(@Path("id") id: Int, @Body note: Note): Note

}