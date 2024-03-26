package co.nes.parceltestkmp

import android.app.Application
import co.nes.parceltestkmp.koin.AndroidMainModules
import co.nes.parceltestkmp.koin.SharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            if (BuildConfig.DEBUG) {
                androidLogger(level = Level.DEBUG)
            }

            modules(
                AndroidMainModules.getDatabaseModule(),
                AndroidMainModules.getDetailsModule(),

                SharedModules.getDispatcherProviderModule(),
                SharedModules.getVacationModule(),
            )
        }
    }
}
