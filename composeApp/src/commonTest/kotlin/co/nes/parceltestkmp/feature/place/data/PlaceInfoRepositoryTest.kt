package co.nes.parceltestkmp.feature.place.data

import app.cash.turbine.test
import co.nes.parceltestkmp.ParcelAndroidTest
import co.nes.parceltestkmp.feature.place.data.local.PlaceDatabase
import co.nes.parceltestkmp.feature.place.data.remote.PlaceRemoteSource
import co.nes.parceltestkmp.feature.place.model.Category
import co.nes.parceltestkmp.feature.place.model.Place
import co.nes.parceltestkmp.feature.place.model.PlaceDetailDTO
import co.nes.parceltestkmp.feature.place.model.VacationInfoDTO
import co.nes.parceltestkmp.providers.DispatcherProvider
import conesparceltestkmp.PlaceEntity
import io.mockative.Mock
import io.mockative.any
import io.mockative.coEvery
import io.mockative.coVerify
import io.mockative.every
import io.mockative.mock
import io.mockative.reset
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

class PlaceInfoRepositoryTest : ParcelAndroidTest() {
    @Mock
    private val placeService = mock(PlaceRemoteSource::class)

    @Mock
    private val placeDatabase = mock(PlaceDatabase::class)

    @Mock
    private val dispatcherProvider = mock(DispatcherProvider::class)


    private lateinit var placeInfoRepository: PlaceInfoRepository

    @BeforeTest
    override fun setUp() {
        super.setUp()
        placeInfoRepository = PlaceInfoRepositoryImpl(
            placeService = placeService,
            placeDatabase = placeDatabase,
            dispatcherProvider = dispatcherProvider,
        )

        every { dispatcherProvider.getIO() }.returns(testDispatcher)
    }

    @AfterTest
    override fun tearDown() {
        reset(placeService)
        reset(placeDatabase)
        reset(dispatcherProvider)
        super.tearDown()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `getVacationInfoData should return remote data if cache is null`() =
        runTest {
            // Given
            val expectedVacationInfo = VACATION_INFO

            coEvery { placeService.fetchVacationInfo() }.returns(flowOf(expectedVacationInfo))

            // When
            placeInfoRepository.getVacationInfoData().test {

                val vacationResponse = awaitItem()

                // Then
                assertEquals(expectedVacationInfo, vacationResponse)

                awaitComplete()
            }
        }

    @Test
    fun `fetchVacationInfo should return data from remote source and save places in database`() =
        runTest {
            // Given
            val expectedVacationInfo = VACATION_INFO

            coEvery { placeService.fetchVacationInfo() }.returns(flowOf(expectedVacationInfo))

            // When
            placeInfoRepository.fetchVacationInfo().test {

                val vacationResponse = awaitItem()

                // Then
                assertEquals(expectedVacationInfo, vacationResponse)

                coVerify { placeDatabase.savePlaceEntity(any()) }.wasInvoked(exactly = 2)

                awaitComplete()
            }
        }

    @Test
    fun `fetchPlaceDetailsInfo should return data from remote source and save place in database if not exist`() =
        runTest {
            // Given
            val locationId = "1"
            val expectedPlaceDetail = PLACE_DETAIL

            coEvery { placeService.fetchPlaceDetailPlace(locationId) }.returns(
                flowOf(
                    expectedPlaceDetail
                )
            )
            coEvery { placeDatabase.getPlaceEntity(expectedPlaceDetail.name) }.returns(flowOf(null))

            // When
            placeInfoRepository.fetchPlaceDetailsInfo(locationId).test {

                val placeDetailResponse = awaitItem()

                // Then
                assertEquals(expectedPlaceDetail, placeDetailResponse)

                coVerify { placeDatabase.savePlaceEntity(any()) }.wasInvoked(exactly = 1)

                awaitComplete()
            }
        }

    @Test
    fun `fetchPlaceDetailsInfo should return data from remote source and update place in database if exist`() =
        runTest {
            // Given
            val locationId = "1"
            val expectedPlaceDetail = PLACE_DETAIL

            val databaseEntity = PLACE_ENTITY.copy(name = expectedPlaceDetail.name)

            coEvery { placeService.fetchPlaceDetailPlace(locationId) }.returns(
                flowOf(
                    expectedPlaceDetail
                )
            )
            coEvery { placeDatabase.getPlaceEntity(expectedPlaceDetail.name) }.returns(
                flowOf(
                    databaseEntity
                )
            )

            // When
            placeInfoRepository.fetchPlaceDetailsInfo(locationId).test {

                val placeDetailResponse = awaitItem()

                // Then
                assertEquals(expectedPlaceDetail, placeDetailResponse)

                coVerify { placeDatabase.updatePlaceEntity(any()) }.wasInvoked(exactly = 1)

                awaitComplete()
            }
        }

    @Test
    fun `getAllPlaceInfo should return all places from database`() =
        runTest {
            // Given
            val expectedPlaces = listOf(PLACE_ENTITY)

            coEvery { placeDatabase.getAllPlaces() }.returns(flowOf(expectedPlaces))

            // When
            placeInfoRepository.getAllPlaceInfo().test {

                val placesResponse = awaitItem()

                // Then
                assertEquals(expectedPlaces, placesResponse)

                awaitComplete()
            }
        }

    @Test
    fun `getPlaceByName should return place from database by name`() =
        runTest {
            // Given
            val name = "Place 1"
            val expectedPlace = PLACE_ENTITY

            coEvery { placeDatabase.getPlaceEntity(name) }.returns(flowOf(expectedPlace))

            // When
            placeInfoRepository.getPlaceByName(name).test {

                val placeResponse = awaitItem()

                // Then
                assertEquals(expectedPlace, placeResponse)

                awaitComplete()
            }
        }

    @Test
    fun `updatePlaceInfo should update place in database`() =
        runTest {
            // Given
            val place = PLACE_ENTITY

            // When
            placeInfoRepository.updatePlaceInfo(place)

            // Then
            coVerify { placeDatabase.updatePlaceEntity(place) }.wasInvoked()
        }

    companion object {
        val PLACE_DETAIL = PlaceDetailDTO(
            name = "Place 1",
            rating = 4.5,
            reviews = 100,
            description = "Description",
            facilities = listOf("Facility 1", "Facility 2"),
            price = 100.0,
            latitude = 0.0,
            longitude = 0.0,
            isFavorite = true
        )

        val PLACE_ENTITY = PlaceEntity(
            name = "Place 2",
            score = 4.0,
            reviews = 50,
            description = "Old description",
            facilities = "Old Facility 1, Old Facility 2",
            price = 50.0,
            latitude = 0.0,
            longitude = 0.0,
            isFavorite = 1,
            section = 0,
            urlImage = ""
        )

        val VACATION_INFO = VacationInfoDTO(
            places =
            Category(
                id = "1",
                popular = listOf(
                    Place(
                        name = "Place 1",
                        imageUrl = "https://place1.com",
                        isFavorite = false,
                        rating = 4.5
                    )
                ),
                recommended = listOf(
                    Place(
                        name = "Place 2",
                        imageUrl = "https://place2.com",
                        isFavorite = true,
                        rating = 4.8
                    )
                )
            )
        )
    }

}
