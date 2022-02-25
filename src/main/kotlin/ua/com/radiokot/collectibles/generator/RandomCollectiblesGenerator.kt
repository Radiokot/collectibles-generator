package ua.com.radiokot.collectibles.generator

import ua.com.radiokot.collectibles.generator.model.GenerationPoolEntry
import ua.com.radiokot.collectibles.generator.model.GeneratedCollectible

/**
 * Simple collectibles generator that takes parts from the pool
 *
 * @param orderedPool ordered set of parts
 */
class RandomCollectiblesGenerator(
    private val orderedPool: List<Collection<GenerationPoolEntry>>
) : CollectiblesGenerator {

    override fun generate(): GeneratedCollectible {
        return GeneratedCollectible(
            parts = orderedPool
                .mapNotNull { poolEntries ->
                    val choice = poolEntries.weightedRandom()
                    if (choice is GenerationPoolEntry.Image) {
                        choice to poolEntries.probabilityOf(choice)
                    } else {
                        null
                    }
                }
                .map { (poolEntry, probability) ->
                    GeneratedCollectible.Part(poolEntry, probability)
                }
        )
    }
}