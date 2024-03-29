package co.nes.parceltestkmp.common.mvi.mvi

interface Action {
    suspend fun execute()
}
