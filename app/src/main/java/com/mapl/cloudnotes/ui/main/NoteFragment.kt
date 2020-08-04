package com.mapl.cloudnotes.ui.main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.os.Environment.*
import android.view.View
import androidx.fragment.app.Fragment
import com.mapl.cloudnotes.R
import kotlinx.android.synthetic.main.note_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File
import java.util.*


class NoteFragment : Fragment(R.layout.note_fragment) {
    private val sharedViewModel: NoteViewModel by sharedViewModel()
    private val SELECT_PICTURE = 10

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectImage()

        sharedViewModel.imageStateLD.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { state ->
                state.bitmap.let {
                    mainImage.setImageBitmap(it)
                }
            })
    }

    fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.data != null && requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            val imageStream = context?.contentResolver?.openInputStream(data.data!!)
            val file = File(
                context?.getExternalFilesDir(DIRECTORY_PICTURES),
                "${UUID.randomUUID()}.png"
            )
            if (imageStream != null) {
                sharedViewModel.saveToPng(imageStream, file)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedViewModel.onDestroy()
    }
}