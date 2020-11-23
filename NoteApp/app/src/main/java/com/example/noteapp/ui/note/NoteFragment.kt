package com.example.noteapp.ui.note

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.ui.adapter.NoteAdapter
import com.example.noteapp.ui.viewmodel.NoteViewModel
import com.example.noteapp.ultis.Status
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteFragment : Fragment(R.layout.fragment_note_list) {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(requireActivity().application)
        )[NoteViewModel::class.java]
    }
    private val test by viewModels<NoteViewModel>()
    private val adapter: NoteAdapter by lazy {
        NoteAdapter(requireContext(), onItemClick, onItemDelete)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)


        rv_note.setHasFixedSize(true)
        rv_note.layoutManager = LinearLayoutManager(requireContext())
        rv_note.adapter = adapter

        swipe_layout.setOnRefreshListener {
            refreshData()
        }

        refreshData()
    }

    private val onItemClick: (Note) -> Unit = {

    }
    private val onItemDelete: (Note) -> Unit = {

    }

    private fun refreshData(){
        noteViewModel.getNotesFromApi().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        swipe_layout.isRefreshing = false
                        resource.data?.let { notes -> adapter.setNotes(notes) }
                    }
                    Status.ERROR -> {
                        swipe_layout.isRefreshing = false
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        getNoteFromDatabase()
                    }
                    Status.LOADING -> {
                        swipe_layout.isRefreshing = true
                    }
                }
            }
        })
    }

    private fun getNoteFromDatabase(){
        noteViewModel.getAllNote().observe(viewLifecycleOwner,{
            adapter.setNotes(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val controller= findNavController()
        btn_open_add_activity.setOnClickListener {
            controller.navigate(R.id.action_noteFragment_to_addNoteFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.mnu_sync){
            Toast.makeText(requireContext(), "Syncing", Toast.LENGTH_LONG).show()
            noteViewModel.getNotesFromApi().observe(viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            swipe_layout.isRefreshing = false
                            noteViewModel.deleteAllNote()
                            it.data?.let {notes->
                                noteViewModel.insertAllNoteToDatabase(notes)
                            }
                        }
                        Status.ERROR -> {
                            swipe_layout.isRefreshing = false
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            swipe_layout.isRefreshing = true
                        }
                    }
                }
            })
        }
        return true

    }

}