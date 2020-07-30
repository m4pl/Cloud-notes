package com.mapl.cloudnotes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.mapl.cloudnotes.Prefs

class MainViewModel(private val prefs: Prefs) : ViewModel() {
    private val clickedState = LiveEvent<String>()
    val state: LiveData<String> = clickedState

    fun openNoteFragment(){
        clickedState.value = "${if (clickedState.value == null) "" else clickedState.value} clicked!"
    }
}