package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.common.mvi.mvi.GeneralIntentAspenLogger
import co.nes.parceltestkmp.feature.home.mvi.place.factory.PlaceListActionFactory
import co.nes.parceltestkmp.feature.home.mvi.place.factory.PlaceListReducerFactory
import co.nes.parceltestkmp.feature.home.mvi.place.viewmodel.HomeViewModel
import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepository
import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepositoryImpl
import co.nes.parceltestkmp.feature.place.data.local.PlaceDatabase
import co.nes.parceltestkmp.feature.place.data.local.PlaceDatabaseImpl
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteImpl
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteSource
import co.nes.parceltestkmp.feature.place.detail.factory.PlaceDetailsActionFactory
import co.nes.parceltestkmp.feature.place.detail.factory.PlaceDetailsReducerFactory
import co.nes.parceltestkmp.feature.place.detail.viewmodel.PlaceDetailViewModel
import co.nes.parceltestkmp.providers.DefaultDispatcherProvider
import co.nes.parceltestkmp.providers.DispatcherProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object SharedModules {
    fun getDispatcherProviderModule() = module {
        singleOf(::DefaultDispatcherProvider) { bind<DispatcherProvider>() }
    }

    fun getVacationModule() = module {

        singleOf(::PlaceDatabaseImpl) { bind<PlaceDatabase>() }
        singleOf(::PlaceRemoteImpl) { bind<PlaceRemoteSource>() }
        singleOf(::PlaceInfoRepositoryImpl) { bind<PlaceInfoRepository>() }

        singleOf(::PlaceListActionFactory) { bind<PlaceListActionFactory>() }
        singleOf(::PlaceListReducerFactory) { bind<PlaceListReducerFactory>() }
        singleOf(::GeneralIntentAspenLogger) { bind<GeneralIntentAspenLogger<Any>>() }

        singleOf(::PlaceDetailsActionFactory) { bind<PlaceDetailsActionFactory>() }
        singleOf(::PlaceDetailsReducerFactory) { bind<PlaceDetailsReducerFactory>() }

        factoryOf(::HomeViewModel) { bind<HomeViewModel>() }
        factoryOf(::PlaceDetailViewModel) { bind<PlaceDetailViewModel>() }
    }
}
