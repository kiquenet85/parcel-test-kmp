package co.nes.parceltestkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
