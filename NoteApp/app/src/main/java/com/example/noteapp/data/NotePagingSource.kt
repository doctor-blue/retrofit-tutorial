package com.example.noteapp.data

import androidx.paging.PagingSource
import com.example.noteapp.api.NoteApi
import com.example.noteapp.data.NoteRepository.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

private const val NOTE_STARTING_PAGE_INDEX = 1

class NotePagingSource(
    private val noteApi: NoteApi
) : PagingSource<Int, Note>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
        val position = params.key ?: NOTE_STARTING_PAGE_INDEX
        return try {
            val notes = noteApi.getAllNote(position, params.loadSize)
            LoadResult.Page(
                data = notes,
                prevKey = if (position == NOTE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (notes.isEmpty()) null else position + (params.loadSize / NETWORK_PAGE_SIZE)
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}