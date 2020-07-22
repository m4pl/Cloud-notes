package com.mapl.cloudnotes.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mapl.cloudnotes.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NoteFragment:Fragment(R.layout.note_fragment) {
    private val sharedViewModel: NoteViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}