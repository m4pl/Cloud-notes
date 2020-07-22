package com.mapl.cloudnotes

import android.app.Application
import com.mapl.cloudnotes.ui.main.MainViewModel
import com.mapl.cloudnotes.ui.main.NoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
        Timber.plant(Timber.DebugTree())
        Timber.v("App started")
    }
}

val appModule = module {
    single { Prefs(androidApplication()) }

    viewModel { MainViewModel(get()) }
    viewModel { NoteViewModel(get()) }
}