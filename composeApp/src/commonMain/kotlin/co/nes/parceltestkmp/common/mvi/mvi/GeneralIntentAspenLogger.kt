package co.nes.parceltestkmp.common.mvi.mvi


open class GeneralIntentAspenLogger<VIEWINTENT> : IntentLogger<VIEWINTENT>{

    override fun log(intent: VIEWINTENT) {
        println("$INTENT_MESSAGE_LOG $intent")
    }
}
