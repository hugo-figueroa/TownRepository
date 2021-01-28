package com.android.towndirectory.core

import android.app.Application
import android.content.Context
import com.android.towndirectory.core.di.ApplicationComponent
import com.android.towndirectory.core.di.DaggerApplicationComponent

class TownApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): ApplicationComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}