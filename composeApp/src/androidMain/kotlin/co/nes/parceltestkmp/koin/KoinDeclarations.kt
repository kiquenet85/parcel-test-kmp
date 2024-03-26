package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.data_access.database.DriverFactory
import org.koin.dsl.module

object AndroidMainModules {
    fun getDatabaseModule() = module {
        single { DriverFactory(get()).createDatabase() }
    }
}
