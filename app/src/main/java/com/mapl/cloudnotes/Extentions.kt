package com.mapl.cloudnotes

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

@MainThread
inline fun <T> MutableLiveData<T>.update(crossinline block: (T) -> T) {
    value?.let { value = block(it) }
}
