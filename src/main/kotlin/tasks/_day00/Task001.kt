package tasks._day00

import AbstractTask

class Task001 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, Int>() {

    override fun calculate(): Int {
        return 0
    }
}