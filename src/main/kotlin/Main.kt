import tasks.day04.Task08
import java.io.File

fun Array<String>.main() {
    doTask()
//    runBenchmark { doTask() }
}

private fun doTask(): Float {
    val fileNameTask = "src/main/kotlin/tasks/day04/task_input.txt"
    val inputData = File(fileNameTask)
        .readLines()
        .map { it }

    val task = Task08(inputData)
    return task.executeTask()
}

fun Any.log() {
    println(this.toString())
}

inline fun runBenchmark(repeatCount: Int = 10, task: () -> Float) {
    val times = mutableListOf<Float>()
    repeat(repeatCount) {
        times.add(task())
    }
    times.remove(times.max())
    times.remove(times.min())
    println("----------------")
    println("Result of the benchmark is:")
    println("Task was completed $repeatCount times and in average it took ${times.average()} ms")
}