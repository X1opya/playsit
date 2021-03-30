package dev.playsit.core.utils

import androidx.lifecycle.*

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this
}