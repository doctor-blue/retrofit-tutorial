package com.example.noteapp.ui.note

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.data.Note
import com.example.noteapp.ui.viewmodel.NoteViewModel
import com.example.noteapp.ultis.Status
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddNoteFragment:Fragment(R.layout.fragment_add_note) {

    val navController by lazy {
        findNavController()
    }


    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(requireActivity().application)
        )[NoteViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {

            val note = Note(edt_note_title.text.toString(),edt_note_des.text.toString())

            noteViewModel.addNoteToServer(note).observe(viewLifecycleOwner, {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(requireContext(), it.data?.toString(), Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                        }
                        Status.ERROR -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()

                        }
                        Status.LOADING -> {

                        }
                    }
                }
            })
        }
    }

}