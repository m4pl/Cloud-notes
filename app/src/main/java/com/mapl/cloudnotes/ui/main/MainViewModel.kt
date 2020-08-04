package com.mapl.cloudnotes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.mapl.cloudnotes.Prefs
import com.mapl.cloudnotes.update
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject


class MainViewModel(private val prefs: Prefs) : ViewModel() {
    private val clickedState = LiveEvent<String>()
    val clickedStateLD: LiveData<String> = clickedState

    private val textState = MutableLiveData(NoteTextState())
    val textStateLD: LiveData<NoteTextState> = textState

    private val disposables = CompositeDisposable()
    private val subject = PublishSubject.create<String>()

    init {
        disposables.add(subject.subscribe { string ->
            textState.update {
                it.copy(
                    text = string
                )
            }
        })
    }

    fun openNoteFragment() {
        clickedState.value =
            "${if (clickedState.value == null) "" else clickedState.value} clicked!"
    }

    fun setText(text: String) {
        subject.onNext(text)
    }

    fun onDestroy() {
        disposables.clear()
    }
}

data class NoteTextState(
    val text: String = ""
)