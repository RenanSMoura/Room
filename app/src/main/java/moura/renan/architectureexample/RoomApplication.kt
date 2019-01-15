package moura.renan.architectureexample

import android.app.Application
import moura.renan.architectureexample.presentation.injection.applicationModules
import org.koin.android.ext.android.startKoin

class RoomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf( applicationModules))
    }
}