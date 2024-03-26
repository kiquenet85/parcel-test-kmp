package co.nes.parceltestkmp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertSame

@OptIn(ExperimentalCoroutinesApi::class)
open class ParcelAndroidTest {

    protected val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    open fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    open fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun  simpleTest() {
        val a = 1
        assertSame(1, a)
    }
}