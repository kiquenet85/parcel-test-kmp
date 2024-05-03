package co.nes.parceltestkmp
actual fun getPlatformName(): String = "Desktop ${System.getProperty("os.name")}"
