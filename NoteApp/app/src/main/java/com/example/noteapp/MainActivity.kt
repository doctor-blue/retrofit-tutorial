package com.example.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.ui.viewmodel.NoteViewModel


class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }
/*    private val adapter: NoteAdapter by lazy {
        NoteAdapter(this@MainActivity, onItemClick, onItemDelete)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initControls()
        initEvents()
    }

    private fun initEvents() {

    }

    private fun initControls() {

    }

    /* private val onItemClick: (Note) -> Unit = {
         val intent = Intent(this, UpdateNoteActivity::class.java)
         intent.putExtra("UPDATE_NOTE", it)
         startActivity(intent)

     }
     private val onItemDelete: (Note) -> Unit = {
         noteViewModel.deleteNote(it)
     }*/
}