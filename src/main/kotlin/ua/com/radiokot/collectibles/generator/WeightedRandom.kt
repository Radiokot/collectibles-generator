package ua.com.radiokot.collectibles.generator

import ua.com.radiokot.collectibles.generator.model.WeightedEntry
import kotlin.random.Random

/**
 * Picks a random entry regarding items weights.
 */
fun <T : WeightedEntry> Collection<T>.weightedRandom(): T {
    if (isEmpty()) {
        throw NoSuchElementException("Collection is empty")
    }

    val weightSum = sumBy(WeightedEntry::weight)
    val choice = Random.nextInt(0, weightSum + 1)

    var cumulativeWeight = 0

    forEach { entry ->
        if ((cumulativeWeight..cumulativeWeight + entry.weight).contains(choice)) {
            return entry
        }
        cumulativeWeight += entry.weight
    }

    return last()
}

/**
 * @return probability of picking the [entry] by [weightedRandom] in percents.
 */
fun <T : WeightedEntry> Collection<T>.probabilityOf(entry: T): Double {
    val weightSum = sumBy(WeightedEntry::weight)
    return entry.weight.toDouble() / weightSum
}