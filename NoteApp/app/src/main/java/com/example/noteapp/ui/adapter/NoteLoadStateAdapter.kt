package com.example.noteapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R

class NoteLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<NoteLoadStateAdapter.LoadStateViewHolder>() {
    class LoadStateViewHolder(itemView: View, retry: () -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val btnRetry: Button = itemView.findViewById(R.id.btn_retry)
        private val txtError: TextView = itemView.findViewById(R.id.txt_error)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)

        init {
            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun onBind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                txtError.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
            txtError.isVisible = loadState !is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_load_state_view_item, parent, false)
        return LoadStateViewHolder(view, retry)
    }
}