package com.mapl.cloudnotes.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mapl.cloudnotes.R
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModel()
    private val sharedViewModel: NoteViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddNewNote.setOnClickListener {
            viewModel.openNoteFragment()
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_noteFragment)
        })
    }
}