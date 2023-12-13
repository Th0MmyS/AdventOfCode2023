package tasks._day00

import AbstractTask

class Task constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        println("Ready to go")
        return 0
    }
}