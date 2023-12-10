package tasks.day05

import AbstractTask

class Task09 constructor(
    override var inputData: List<String>
) : AbstractTask<List<String>, UInt>() {

    override fun calculate(): UInt {
        var rawSeeds = ""
        val rawMappings = mutableListOf<MutableList<String>>()
        var tempList = mutableListOf<String>()

        inputData.forEachIndexed { index, s ->
            if (index == 0) {
                rawSeeds = s
            } else if (s.isNotBlank() && s.first().isDigit()) {
                tempList.add(s)
                if (index == inputData.size - 1) {
                    // last line, finish reading
                    rawMappings.add(tempList)
                }
            } else {
                if (tempList.isNotEmpty()) {
                    rawMappings.add(tempList)
                    tempList = mutableListOf()
                }
            }
        }

        val seeds = processSeeds(rawSeeds)
        val mappings = processMappings(rawMappings)

        val mappedSeeds = seeds.map { seed ->
            var calculatedSeed = seed
            mappings.forEach { mapping ->
                mapping.firstOrNull { calculatedSeed in it.source..it.source + it.range }
                    ?.let {
                        calculatedSeed = calculatedSeed - it.source + it.destination
                    }
            }

            calculatedSeed
        }

        return mappedSeeds.min()
    }

    private fun processMappings(rawMappings: MutableList<MutableList<String>>): MutableList<List<Mapping>> {
        val mappings = mutableListOf<List<Mapping>>()

        rawMappings.forEach {
            mappings.add(processMap(it))
        }

        return mappings
    }

    private fun processMap(map: MutableList<String>) = map.map {
        val split = it.split(" ")
        val dest = split.first().toUInt()
        val range = split.last().toUInt()
        val source = split[1].toUInt()
        Mapping(source, dest, range)
    }

    private fun processSeeds(rawSeeds: String) = rawSeeds.split(":")
        .last()
        .split(" ")
        .mapNotNull {
            it.replace(" ", "").toUIntOrNull()
        }
}

data class Mapping constructor(
    val source: UInt,
    val destination: UInt,
    val range: UInt,
)