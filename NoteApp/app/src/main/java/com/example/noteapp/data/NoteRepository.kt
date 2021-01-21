package com.example.noteapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.noteapp.api.AppConfig
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.dao.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(app: Application) {

    private val noteDao: NoteDao

    init {
        val noteDatabase: NoteDatabase = NoteDatabase.getInstance(app)
        noteDao = noteDatabase.getNoteDao()
    }

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getAllNote(): LiveData<List<Note>> = noteDao.getAllNote()

    fun getNotesFromApi(): Flow<PagingData<Note>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { NotePagingSource(AppConfig.apiService) }
    ).flow

    suspend fun addNoteToServer(note: Note) = AppConfig.apiService.addNote(note)

    suspend fun deleteAllNoteFromDatabase() = noteDao.deleteAll()
    suspend fun insertAllNoteToDatabase(notes: List<Note>) = noteDao.addAllNote(notes)

    companion object {
         const val NETWORK_PAGE_SIZE = 20
    }
}