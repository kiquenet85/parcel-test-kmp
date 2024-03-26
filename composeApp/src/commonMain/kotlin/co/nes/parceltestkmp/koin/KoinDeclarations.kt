package co.nes.parceltestkmp.koin

import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepository
import co.nes.parceltestkmp.feature.place.data.PlaceInfoRepositoryImpl
import co.nes.parceltestkmp.feature.place.data.local.PlaceInfoDatabase
import co.nes.parceltestkmp.feature.place.data.local.PlaceInfoDatabaseImpl
import co.nes.parceltestkmp.feature.place.data.remote.PlaceDetailRemoteSource
import co.nes.parceltestkmp.feature.place.data.remote.PlaceDetailRemoteSourceImpl
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
        singleOf(::PlaceInfoDatabaseImpl) { bind<PlaceInfoDatabase>() }
        singleOf(::PlaceDetailRemoteSourceImpl) { bind<PlaceDetailRemoteSource>() }
        singleOf(::PlaceInfoRepositoryImpl) { bind<PlaceInfoRepository>() }
    }
}
