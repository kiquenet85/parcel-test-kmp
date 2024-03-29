package co.nes.parceltestkmp.common.mvi.mvi

class ConsumableValue<T>(val data: T) {

    private var consumed = false

    fun consume(body: ConsumableValue<T>.(T) -> Unit) {
        if (!consumed) {
            consumed = true
            body(data)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        //if (javaClass != other?.javaClass) return false

        other as ConsumableValue<*>

        if (data != other.data) return false
        if (consumed != other.consumed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = data?.hashCode() ?: 0
        result = 31 * result + consumed.hashCode()
        return result
    }


}
