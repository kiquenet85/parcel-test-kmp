package co.nes.parceltestkmp.common.mvi.mvi

interface Reducer<VIEWSTATE> {

    suspend fun reduce(viewState: VIEWSTATE): VIEWSTATE
}
