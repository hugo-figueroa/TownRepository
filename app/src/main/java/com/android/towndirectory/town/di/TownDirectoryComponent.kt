package com.android.towndirectory.town.di

import com.android.towndirectory.town.ui.TownDirectoryFragment
import dagger.Subcomponent

@Subcomponent(modules = [TownDirectoryModule::class])
interface TownDirectoryComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TownDirectoryComponent
    }

    fun inject(fragment: TownDirectoryFragment)

}