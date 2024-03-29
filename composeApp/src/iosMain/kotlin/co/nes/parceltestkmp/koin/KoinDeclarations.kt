package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.data_access.database.DriverFactory
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.place.detail.DetailsViewModelDemo
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object IOSMainModules {
    fun getDatabaseModule() = module {
        single { DriverFactory().createDatabase() }
    }

    fun getDetailsModule() = module {
        factoryOf(::DetailsViewModelDemo)
        factoryOf(::HomeViewModel)
        factoryOf(::PlaceDetailViewModel)
    }
}
