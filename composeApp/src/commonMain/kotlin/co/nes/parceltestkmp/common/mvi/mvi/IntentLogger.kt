package co.nes.parceltestkmp.common.mvi.mvi

const val INTENT_MESSAGE_LOG = "Handling intent:"

interface IntentLogger<VIEWINTENT> {
    fun log(intent: VIEWINTENT)
}
