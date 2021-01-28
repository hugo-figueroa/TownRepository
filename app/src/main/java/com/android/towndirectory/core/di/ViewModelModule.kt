package com.android.towndirectory.core.di

import androidx.lifecycle.ViewModel
import com.android.towndirectory.town.viewmodel.TownsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TownsViewModel::class)
    abstract fun bindTownDirectoryViewModel(viewModel: TownsViewModel): ViewModel
}