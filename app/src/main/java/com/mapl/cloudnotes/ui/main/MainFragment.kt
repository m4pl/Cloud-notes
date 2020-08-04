package com.mapl.cloudnotes.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.mapl.cloudnotes.R
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(R.layout.main_fragment) {
    private val viewModel: MainViewModel by viewModel()
    private val sharedViewModel: NoteViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setText(s.toString())
            }
        })

        btnAddNewNote.setOnClickListener {
            viewModel.openNoteFragment()
        }

        viewModel.clickedStateLD.observe(viewLifecycleOwner, Observer {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_noteFragment)
        })

        viewModel.textStateLD.observe(viewLifecycleOwner, Observer {
            textView.text = it.text
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}