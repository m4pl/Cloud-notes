package com.mapl.cloudnotes.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mapl.cloudnotes.Prefs
import com.mapl.cloudnotes.update
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class NoteViewModel(private val prefs: Prefs) : ViewModel() {
    private val imageState = MutableLiveData(ImageState())
    val imageStateLD: LiveData<ImageState> = imageState

    private val disposables = CompositeDisposable()
    lateinit var bitmap: Bitmap

    fun saveToPng(inputStream: InputStream, file: File) {
        disposables.add(Completable.create { emitter: CompletableEmitter ->
            bitmap = BitmapFactory.decodeStream(inputStream)
            convertAndSaveToPNG(bitmap, file)
            emitter.onComplete()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                imageState.update { it.copy(bitmap = bitmap) }
            })
    }

    private fun convertAndSaveToPNG(bitmap: Bitmap, file: File) {
        try {
            val fileOutputStream: FileOutputStream? = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onDestroy() {
        disposables.clear()
    }
}

data class ImageState(
    val bitmap: Bitmap? = null
)