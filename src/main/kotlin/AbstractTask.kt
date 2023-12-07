abstract class AbstractTask<T, R> : ICalculation<R> {

    abstract var inputData: T

    private fun performCalculation(): Pair<R, Float> {
        val startTimeInMillis = System.nanoTime()
        val result = calculate()
        val time = (System.nanoTime() - startTimeInMillis) / 1_000_000f
        println(
            buildString {
                append("Task took $time ms to finish ")
                if (time > WTF_LIMIT) {
                    append("\n----- WTF??? Optimize it!!! -----")
                }
            }
        )
        return Pair(result, time)
    }

    fun executeTask(): Float {
        println("Calculating ${javaClass.name}")
        val result = performCalculation()
        println("Calculated result is ${result.first}")
        println("-----")
        return result.second
    }

    private companion object {
        const val WTF_LIMIT = 5000L
    }
}