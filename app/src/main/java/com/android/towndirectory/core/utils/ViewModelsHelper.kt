package com.android.towndirectory.core.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//A generic function that will just make our code a bit nicer when requesting viewModels
inline fun <reified T : ViewModel> Fragment.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
    ViewModelProvider(this, viewModelFactory)[T::class.java]

//A generic function that will just make our code a bit nicer when requesting viewModels from Activity
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
    ViewModelProvider(this, viewModelFactory)[T::class.java]


inline fun <T> LiveData<T>.subscribe(
    lifecycle: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
) {
    observe(lifecycle, Observer { it?.run(onChanged) })
}