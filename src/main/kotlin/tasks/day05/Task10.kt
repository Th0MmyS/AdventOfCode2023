package tasks.day05

import AbstractTask
import kotlinx.coroutines.*
import log

/**
 * Bruteforce method, optimized by running each seed range in separate coroutine.
 * Still takes more than a minute.
 * Better solution would be to work with the ranges only and not with concrete seeds.
 * Too lazy to refactor, maybe later
 */
class Task10 constructor(
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

        var lowestNumber = UInt.MAX_VALUE

        val seeds = processSeeds(rawSeeds)
        val mappings = processMappings(rawMappings)

        var source: UInt
        var count: UInt

        runBlocking {
            launch(Dispatchers.Default) {
                seeds.map {
                    async {
                        var calculatedSeed: UInt

                        source = it.first()
                        count = it.last()

                        UIntRange(source, source + count).asSequence().forEach { seed ->
                            calculatedSeed = seed
                            mappings.forEach { mapping ->
                                mapping.firstOrNull { m -> calculatedSeed in m.source..m.source + m.range }
                                    ?.let { m ->
                                        calculatedSeed = calculatedSeed - m.source + m.destination
                                    }
                            }

                            if (calculatedSeed < lowestNumber) {
                                lowestNumber = calculatedSeed
                            }
                        }
                        lowestNumber.log()
                    }
                }.awaitAll()
            }
        }

        return lowestNumber
    }

    private fun processMappings(rawMappings: MutableList<MutableList<String>>): Sequence<List<Mapping>> {
        val mappings = mutableListOf<List<Mapping>>()

        rawMappings.forEach {
            mappings.add(processMap(it))
        }

        return mappings.asSequence()
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
        }.chunked(2)
}