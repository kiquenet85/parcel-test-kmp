package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.data_access.database.DriverFactory
import org.koin.core.module.dsl.factoryOf
import co.nes.parceltestkmp.feature.place.detail.DetailsViewModelDemo
import org.koin.dsl.module

object IOSMainModules {
    fun getDatabaseModule() = module {
        single { DriverFactory().createDatabase() }
    }

    fun getDetailsModule() = module {
        factoryOf(::DetailsViewModelDemo)
    }
}
