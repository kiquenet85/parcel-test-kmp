package co.nes.parceltestkmp.providers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class DefaultDispatcherProvider : DispatcherProvider {
    override fun getMain() = Dispatchers.Main
    override fun getIO() = Dispatchers.IO
    override fun getDefault() = Dispatchers.Default
    override fun getUnconfined() = Dispatchers.Unconfined
}
