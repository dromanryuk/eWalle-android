package ru.dromanryuk.ewalle

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.dromanryuk.ewalle.data.di.ewalleDataModule
import ru.dromanryuk.ewalle.presentation.di.ewallePresentationModule

class EwalleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@EwalleApp)
            modules(ewalleDataModule, ewallePresentationModule)
        }
    }
}