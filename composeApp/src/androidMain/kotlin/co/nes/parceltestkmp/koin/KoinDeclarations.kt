package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.data_access.database.DriverFactory
import org.koin.androidx.viewmodel.dsl.viewModelOf
import co.nes.parceltestkmp.feature.place.detail.DetailsViewModelDemo
import org.koin.dsl.module

object AndroidMainModules {
    fun getDatabaseModule() = module {
        single { DriverFactory(get()).createDatabase() }
    }

    fun getDetailsModule() = module {
        viewModelOf(::DetailsViewModelDemo)
    }
}
