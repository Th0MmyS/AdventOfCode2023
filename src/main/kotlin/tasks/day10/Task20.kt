package tasks.day10

import AbstractTask

class Task20 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        println("Ready to go")
        return 0
    }
}