package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.data_access.database.DriverFactory
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.place.detail.DetailsViewModelDemo
import co.nes.parceltestkmp.feature.place.detail.place.viewmodel.PlaceDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object AndroidMainModules {
    fun getDatabaseModule() = module {
        single { DriverFactory(get()).createDatabase() }
    }

    fun getDetailsModule() = module {
        viewModelOf(::DetailsViewModelDemo)
        viewModelOf(::HomeViewModel)
        viewModelOf(::PlaceDetailViewModel)
    }
}
