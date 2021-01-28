package com.android.towndirectory.core.di

import android.content.Context
import com.android.towndirectory.town.di.TownDirectoryComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        ViewModelBuilderModule::class,
        ViewModelModule::class,
        SubcomponentsModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun townDirectoryComponent(): TownDirectoryComponent.Factory

}

@Module(
    subcomponents = [
        TownDirectoryComponent::class
    ]
)
object SubcomponentsModule