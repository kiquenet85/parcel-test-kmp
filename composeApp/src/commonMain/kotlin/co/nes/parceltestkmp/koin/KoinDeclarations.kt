package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepository
import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepositoryImpl
import co.nes.parceltestkmp.feature.place.data.local.PlaceDatabase
import co.nes.parceltestkmp.feature.place.data.local.PlaceDatabaseImpl
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteSource
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteImpl
import co.nes.parceltestkmp.providers.DefaultDispatcherProvider
import co.nes.parceltestkmp.providers.DispatcherProvider
import org.koin.core.module.dsl.bind
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
    }
}
